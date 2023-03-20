package com.firattamur.licenseservice.controller;

import com.firattamur.licenseservice.model.License;
import com.firattamur.licenseservice.service.LicenseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@RestController
@RequestMapping(value = "/v1/organizations/{organizationId}/licenses")
public class LicenseServiceController {

    private final LicenseService licenseService;

    public LicenseServiceController(LicenseService licenseService) {
        this.licenseService = licenseService;
    }

    @GetMapping(value = "/{licenseId}")
    public ResponseEntity<License> getLicense(@PathVariable("organizationId") String organizationId,
                                              @PathVariable("licenseId") String licenseId) {

        License license = licenseService.getLicense(organizationId, licenseId);

        license.add(
                linkTo(methodOn(LicenseServiceController.class).getLicense(organizationId, licenseId)).withSelfRel(),
                linkTo(methodOn(LicenseServiceController.class).createLicense(organizationId, license, Locale.getDefault())).withRel("create"),
                linkTo(methodOn(LicenseServiceController.class).updateLicense(organizationId, license, Locale.getDefault())).withRel("update"),
                linkTo(methodOn(LicenseServiceController.class).deleteLicense(organizationId, licenseId)).withRel("delete")
                );

        return ResponseEntity.ok(license);
    }

    @PutMapping
    public ResponseEntity<String> updateLicense(@PathVariable("organizationId") String organizationId,
                                                @RequestBody License request,
                                                @RequestHeader("Accept-Language") Locale locale) {

        String responseMessage = licenseService.updateLicense(request, organizationId, locale);

        return ResponseEntity.ok(responseMessage);
    }

    @PostMapping
    public ResponseEntity<String> createLicense(@PathVariable("organizationId") String organizationId,
                                                @RequestBody License request,
                                                @RequestHeader("Accept-Language") Locale locale) {

        String responseMessage = licenseService.createLicense(request, organizationId, locale);

        return ResponseEntity.ok(responseMessage);
    }

    @DeleteMapping(value = "/{licenseId}")
    public ResponseEntity<String> deleteLicense(@PathVariable("organizationId") String organizationId,
                                                @PathVariable("licenseId") String licenseId) {

        String responseMessage = licenseService.deleteLicense(licenseId, organizationId);

        return ResponseEntity.ok(responseMessage);
    }

}