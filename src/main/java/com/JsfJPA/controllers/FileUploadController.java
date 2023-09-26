package com.JsfJPA.controllers;

import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.file.UploadedFile;
import org.primefaces.shaded.commons.io.FilenameUtils;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

// Since the files are send asynchronously in parallel to the server, the backing bean has to be @RequestScoped!
@Named
@RequestScoped
public class FileUploadController {
    @Inject
    private FacesContext facesContext;
    @Inject
    private ExternalContext externalContext;
    private UploadedFile uploadedFile;

    public UploadedFile getUploadedFile() {
        return uploadedFile;
    }

    public void setUploadedFile(UploadedFile uploadedFile) {
        this.uploadedFile = uploadedFile;
    }

//    FileUploadListener receives a FileUploadEvent as a parameter.
    public void handleFileUpload(FileUploadEvent event) throws IOException {
        this.uploadedFile = event.getFile();

        Path folder = Paths.get(externalContext.getInitParameter("UPLOAD_LOCATION"));
        String filename = FilenameUtils.getBaseName(uploadedFile.getFileName());
        String extension = FilenameUtils.getExtension(uploadedFile.getFileName());
        Path file = Files.createTempFile(folder, filename + "-", "." + extension);

//        File uploads = new File(externalContext.getInitParameter("UPLOAD_LOCATION"));
//        File file = File.createTempFile("some-filename-", ".ext", uploads);

        try (InputStream input = uploadedFile.getInputStream()) {
            Files.copy(input, file, StandardCopyOption.REPLACE_EXISTING);
        }
        facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "File uploaded!", null));
    }
}
