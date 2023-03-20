package com.firattamur.licenseservice.service;

import com.firattamur.licenseservice.model.License;
import org.springframework.cglib.core.Local;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;
import java.util.Random;

@Service
public class LicenseService {

    MessageSource messageSource;

    public LicenseService(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    public License getLicense(String organizationId, String licenseId) {

        License license = new License();
        license.setId(new Random().nextInt(1000));
        license.setLicenseId(licenseId);
        license.setOrganizationId(organizationId);
        license.setProductName("Software License");
        license.setLicenseType("Per Seat");
        license.setDescription("This is a software license");

        return license;

    }

    public String createLicense(License license, String organizationId, Locale locale) {

        String responseMessage = null;

        if (license != null) {
            license.setOrganizationId(organizationId);
            responseMessage = String.format(messageSource.getMessage("license.create.success", null, locale), organizationId, license);
        }

        return responseMessage;

    }

    public String updateLicense(License license, String organizationId, Locale locale) {

        String responseMessage = null;

        if (license != null) {
            license.setOrganizationId(organizationId);
            responseMessage = String.format(messageSource.getMessage("license.update.success", null, locale), organizationId, license);
        }

        return responseMessage;

    }

    public String deleteLicense(String organizationId, String licenseId) {

        String responseMessage = null;

        if (licenseId != null) {
            responseMessage = String.format("License deleted successfully for organization %s", organizationId);
        }

        return responseMessage;

    }

}
