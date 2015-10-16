package br.com.appdosamba.backend.amazon.s3;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.Collection;

public interface FileStorage {
	URL store(File file, String key);

    URL store(InputStream is, String key, String contentType);
    
    URL store(File file, String rootName, Collection list, String key);
    
    void remove(String key);

    void newBucket(String name);

    URL urlFor(String bucket, String key);
    
    
}
