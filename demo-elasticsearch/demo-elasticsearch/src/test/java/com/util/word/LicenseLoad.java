package com.util.word;

import com.aspose.words.License;

import java.io.InputStream;

public class LicenseLoad {
    public LicenseLoad() {
    }

    public static boolean getLicense() {
        boolean result = false;

        try {
            
            InputStream is = LicenseLoad.class.getClassLoader().getResourceAsStream("license.xml");
            License aposeLic = new License();
            aposeLic.setLicense(is);
            result = true;
        } catch (Exception var3) {
            var3.printStackTrace();
        }

        return result;
    }
}
