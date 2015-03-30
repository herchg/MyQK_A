package my.core.config;

import java.util.HashMap;
import java.util.Map;

import com.google.gson.annotations.SerializedName;
import com.google.gson.annotations.Since;

/**
 * 
 * @author hermeschang
 * 
 */
public class JsonConfig {

    @Since(1.0) @SerializedName("properties")
    public Map<Object, Object> mPropertiesMap = new HashMap<Object, Object>();


    public byte[] getFile(String fileName) {
        return readAssets(getPath(fileName));
    }

    public byte[] getFile(LANG lang, String fileName) {
        return readAssets(getPath(getLangFolder(lang), fileName));
    }
}
