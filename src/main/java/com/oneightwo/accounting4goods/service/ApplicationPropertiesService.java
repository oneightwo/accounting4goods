package com.oneightwo.accounting4goods.service;

import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Service
public class ApplicationPropertiesService {

    public void writeUserAndPassword(String user, String password) {
        String text = "spring.jpa.generate-ddl=true\n" +
                "spring.jpa.database-platform=org.hibernate.dialect.PostgreSQL9Dialect\n" +
                "spring.jpa.hibernate.ddl-auto=update\n" +
                "spring.datasource.url=jdbc:postgresql://localhost:5432/\n" +
                "spring.datasource.driver-class-name=org.postgresql.Driver\n" +
                "spring.datasource.username="+user+"\n" +
                "spring.datasource.password="+password;
        String path = new File("").getAbsolutePath() + "\\src\\main\\resources\\application.properties";
        try(FileOutputStream fos=new FileOutputStream(path))
        {
            byte[] buffer = text.getBytes();
            fos.write(buffer, 0, buffer.length);
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }
        System.out.println("The file has been written");
    }

}
