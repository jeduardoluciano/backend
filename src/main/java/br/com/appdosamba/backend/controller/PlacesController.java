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
import br.com.appdosamba.backend.model.CommercialPlace;
import br.com.appdosamba.backend.repository.PlaceRepository;
import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Delete;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Put;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.observer.upload.UploadedFile;

@Controller
@Path("/places")
public class PlacesController {
	private final Result result;
	private final PlaceRepository placeBusiness;
	private final FileStorage storage;

	private final String PATH_FOLDER_IMAGE = System.getProperty("java.io.tmpdir") + "/img/places/";
	private final String PATH_JSON_LOCAL_FILE = System.getProperty("java.io.tmpdir") + "/json/places/list.json";
	private final String PATTERN_REPLACE_FOLDERNAME = "[^a-zA-Z0-9]+";
	/**
	 * @deprecated CDI eyes only
	 */
	protected PlacesController() {
		this(null, null, null);
	}

	@Inject
	public PlacesController(Result result, FileStorage storage, PlaceRepository placeBusiness) {
		this.result = result;		
		this.placeBusiness = placeBusiness;
		this.storage = storage;
	}

	@Get("/")
	public void list() {
		Collection<CommercialPlace> places = placeBusiness.all();
		result.include("placeList", places);
	}

	@Post("/")
	public void save(CommercialPlace place, UploadedFile photo) {
		File file = null;

		try {
			if (place.getId() != null && photo == null) {
				CommercialPlace p = placeBusiness.find(place.getId());
				place.setLogo(p.getLogo());
			}

			if (photo != null) {
				String dirName = place.getName().replaceAll(PATTERN_REPLACE_FOLDERNAME, "_");
				String fileExt = FilenameUtils.getExtension(photo.getFileName());

				String fileName = "logo." + fileExt;
				file = new File(PATH_FOLDER_IMAGE + dirName, "logo." + fileExt);
				file.getParentFile().mkdirs();
				
				if (!file.isFile())
					photo.writeTo(file);

				String fileFullPathS3 = "img/places/" + dirName + "/" + fileName;

				URL url = storage.store(file, fileFullPathS3);
				place.setLogo(url.toString());
			}
			placeBusiness.save(place);

			saveJSON();
			
		} catch (CommonException | IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Get("/saveJSON")
	public void saveJSON() {
		Collection<CommercialPlace> places = placeBusiness.listPlacesApproved();
		File file = new File(PATH_JSON_LOCAL_FILE);
		storage.store(file, "places", places, "json/places/list.json");

		result.use(json()).withoutRoot().from("sucess").serialize();
	}

	@Delete("/{place.id}")
	public void remove(CommercialPlace place) {
		CommercialPlace del = placeBusiness.find(place.getId());

		if (del != null) {
			if (del.getLogo() != null) {
				try {
					URL url = new URL(del.getLogo());
					String key = url.getPath().substring(1);
					storage.remove(key);
				} catch (MalformedURLException e) {
					throw new RuntimeException(e);
				}
			}
			placeBusiness.remove(del);
							
			saveJSON();
			
		} else {
			result.notFound();
		}
	}

	@Put("/{place.id}")
	public void callUpdate(CommercialPlace place) {
		CommercialPlace update = placeBusiness.find(place.getId());
		result.use(json()).from(update, "place").include("address").serialize();
	}
}
