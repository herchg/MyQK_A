package my.core.config;

import android.content.Context;
/**
 * Created by hermeschang on 2014/3/17.
 * @author hermeschang
 */
public class AssetConfigLoader {

    private final static String ASSET_CONFIG_ROOT = "config";
    private final static String SLASH = "/";

    protected Context mContext;

    public AssetConfigLoader(Context context) {
        mContext = context;
    }

    public byte[] getFile(String fileName) {
        return readAssets(getPath(fileName));
    }

    public byte[] getFile(LANG lang, String fileName) {
        return readAssets(getPath(getLangFolder(lang), fileName));
    }

    private String getPath(String fileName) {
        return ASSET_CONFIG_ROOT + SLASH + fileName;
    }

    private String getPath(String folder, String fileName) {
        return ASSET_CONFIG_ROOT + SLASH + folder + SLASH + fileName;
    }

    /**
     * Return the subfolder associated with the specified language
     * @param lang
     * @return
     */
    private String getLangFolder(LANG lang) {
        if (lang == LANG.Default)
            return LANG.TW.getFolder();  // TODO: 應該要從App去抓目前default的Language
        else
            return lang.getFolder();
    }

    private byte[] readAssets(String fileName) {
        InputStream inputStream = null;
        try {
            inputStream = this.mContext.getAssets().open(fileName);
            return FileUtil.readStreamAsBytes(inputStream);
        }
        catch (Exception ex) {
            logger.error("openAssets({}) exception={}", fileName, ex);
            return null;
        }
        finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                }
                catch(Exception ex) {

                }
            }
        }
    }
}
