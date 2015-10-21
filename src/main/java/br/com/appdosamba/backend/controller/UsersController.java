package br.com.appdosamba.backend.controller;

import static br.com.caelum.vraptor.view.Results.json;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collection;

import javax.inject.Inject;

import org.apache.commons.io.FilenameUtils;

import br.com.appdosamba.backend.amazon.s3.FileStorage;
import br.com.appdosamba.backend.exception.CommonException;
import br.com.appdosamba.backend.model.User;
import br.com.appdosamba.backend.model.common.Gender;
import br.com.appdosamba.backend.model.common.Profile;
import br.com.appdosamba.backend.repository.UserRepository;
import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Delete;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Put;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.observer.upload.UploadedFile;

@Controller
@Path("/users")
public class UsersController {

	private final String PATH_FOLDER_IMAGE = System.getProperty("java.io.tmpdir") + "/img/users/";
	private final String PATTERN_REPLACE_FOLDERNAME = "[^a-zA-Z0-9]+";

	private final Result result;
	private final UserRepository userBusiness;
	private final FileStorage storage;

	/**
	 * @deprecated CDI eyes only
	 */
	protected UsersController() {
		this(null, null, null);
	}

	@Inject
	public UsersController(Result result, UserRepository userBusiness, FileStorage storage) {
		this.result = result;
		this.userBusiness = userBusiness;
		this.storage = storage;
	}

	@Get("/")
	public void list() {
		Collection<User> users = userBusiness.all();
		result.include("userList", users);
		result.include("profiles", Profile.values());
		result.include("genders", Gender.values());
	}

	@Post("/")
	public void save(User user, UploadedFile photo) {

		File file = null;

		try {
			if (user.getId() != null && photo == null) {
				User p = userBusiness.find(user.getId());
				user.setPhoto(p.getPhoto());

			}

			if (photo != null) {
				String dirName = user.getFirstName().replaceAll(PATTERN_REPLACE_FOLDERNAME, "_");
				String fileExt = FilenameUtils.getExtension(photo.getFileName());

				String fileName = "avatar." + fileExt;
				file = new File(PATH_FOLDER_IMAGE + dirName, "avatar." + fileExt);
				file.getParentFile().mkdirs();

				if (!file.isFile())
					photo.writeTo(file);

				String fileFullPathS3 = "img/users/" + dirName + "/" + fileName;

				URL url = storage.store(file, fileFullPathS3);
				
				file.delete();
				user.setPhoto(url.toString());
			}
			userBusiness.save(user);
			result.use(json()).withoutRoot().from("sucess").serialize();

		} catch (CommonException | IOException e) {
			throw new RuntimeException(e);
		}

	}

	@Delete("/{user.id}")
	public void remove(User user) {

		User del = userBusiness.find(user.getId());
		
		if (del.getProfile() == Profile.SUPERADMINISTRATOR){
			result.notFound();
		}else {

			

			if (del != null) {
				if (del.getPhoto() != null) {
					try {
						URL url = new URL(del.getPhoto());
						String key = url.getPath().substring(1);
						storage.remove(key);
					} catch (MalformedURLException e) {
						throw new RuntimeException(e);
					}
				}
				userBusiness.remove(del);
				result.use(json()).withoutRoot().from("sucess").serialize();	
			}
		}
	}

	@Put("/{user.id}")
	public void callUpdate(User user) {
		User update = userBusiness.find(user.getId());
		result.use(json()).from(update, "user").include("profile").exclude("password").serialize();
	}
}
