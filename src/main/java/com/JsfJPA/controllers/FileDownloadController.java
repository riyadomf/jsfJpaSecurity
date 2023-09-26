package com.JsfJPA.controllers;

import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import java.io.*;

@Named
@RequestScoped
public class FileDownloadController {
    private StreamedContent file;

    public FileDownloadController() throws IOException {
        String filePath = "/home/omar/Documents/codes/projects/NBR-eappeal/server-storage/uploads" + "/yourfile.pdf";
        File file_stored = new File(filePath);

        // Declare the InputStream as final
        final InputStream input;

        try {
            input = new FileInputStream(file_stored);

            file = DefaultStreamedContent.builder()
                    .contentType("application/pdf")
                    .name(file_stored.getName())
                    .stream(() -> input)
                    .build();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public StreamedContent getFile() {
        return this.file;
    }
}

//<!--        onstart="PF('pageBlocker').show()"-->
//<!--        oncomplete="PF('pageBlocker').hide()"-->