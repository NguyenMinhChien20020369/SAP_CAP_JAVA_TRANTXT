package customer.jv_trantxt.handlers;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sap.cds.ql.Select;
import com.sap.cds.ql.Update;
import com.sap.cds.ql.cqn.CqnSelect;
import com.sap.cds.ql.cqn.CqnUpdate;
import com.sap.cds.services.ErrorStatuses;
import com.sap.cds.services.ServiceException;
import com.sap.cds.services.cds.CqnService;
import com.sap.cds.services.handler.EventHandler;
import com.sap.cds.services.handler.annotations.After;
import com.sap.cds.services.handler.annotations.Before;
import com.sap.cds.services.handler.annotations.ServiceName;
import com.sap.cds.services.persistence.PersistenceService;

import cds.gen.catalogservice.MediaFiles_;
import cds.gen.catalogservice.CatalogService_;
import cds.gen.catalogservice.MediaFiles;

@Component
@ServiceName(CatalogService_.CDS_NAME)
public class CatalogService implements EventHandler {
    @Autowired
    PersistenceService db;

    @Before(event = CqnService.EVENT_CREATE, entity = MediaFiles_.CDS_NAME)
    public void validateBookAndDecreaseStock(List<MediaFiles> files) {
        List<String> lines = new ArrayList<String>();
        int[] i = { 1 };
        String[] lineResult = { "", "2" };
        StringBuilder sb = new StringBuilder();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        DateTimeFormatter formatterMon = DateTimeFormatter.ofPattern("yyyyMM");

        for (MediaFiles file : files) {
            InputStream lobInputStream = file.getContent();
            try {
                byte[] streamData = lobInputStream.readAllBytes();
                // 2. Tạo ra các InputStream độc lập từ mảng byte[]
                InputStream streamForReading = new ByteArrayInputStream(streamData);
                InputStream streamForSaving = new ByteArrayInputStream(streamData);
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(streamForReading, StandardCharsets.UTF_8));
                reader.lines().forEach(line -> {
                    switch (i[0]) {
                        case 1, 4, 5:
                            lines.add(line);
                            break;

                        case 2:
                            lineResult[1] = sb.append(line.substring(0, 17))
                                    .append(line.substring(25, 31))
                                    .toString();
                            sb.setLength(0);
                            lineResult[0] = sb.append(line.substring(0, 17))
                                    .append("                              1")
                                    .append("  MSC SPARKLE III HV618R               MSC")
                                    .append(LocalDate.now().format(formatter))
                                    .append(LocalDate.now().format(formatter))
                                    .append("                            1")
                                    .append("             HPHHAI PHONG\n")
                                    .append(line.substring(0, 17))
                                    .append(line.substring(25, 31))
                                    .append("               2  ")
                                    .append(LocalDate.now().format(formatter))
                                    .append("000000000000000000                                                                              1\n")
                                    .append(line.substring(0, 17))
                                    .append(line.substring(25, 31))
                                    .append(line.substring(6, 17))
                                    .append("   MSC")
                                    .append(LocalDate.now().format(formatterMon))
                                    .append(LocalDate.now().format(formatter))
                                    .append(LocalDate.now().plusDays(6).format(formatter))
                                    .append(LocalDate.now().format(formatter))
                                    .append(LocalDate.now().plusDays(7).format(formatter))
                                    .append("0000133V        ")
                                    .toString();
                            sb.setLength(0);
                            break;

                        case 3:
                            lineResult[0] = sb.append(lineResult[0])
                                    .append(line.substring(47, 88))
                                    .append("  1   ")
                                    .append(LocalDate.now().format(formatterMon))
                                    .append("\n")
                                    .append(lineResult[1])
                                    .append(line.substring(6, 65))
                                    .append("55Y")
                                    .append("                                   1")
                                    .toString();
                            sb.setLength(0);
                            lines.add(lineResult[0]);
                            break;

                        default:
                            lineResult[0] = sb.append(line.substring(0, 6))
                                    .append("33V                    ")
                                    .append(lineResult[1].substring(6, 17))
                                    .append(line.substring(25, 65))
                                    .append("5146256         000KF47E6707017             ")
                                    .append(LocalDate.now().format(formatter))
                                    .append(line.substring(48, 71))
                                    .toString();
                            sb.setLength(0);
                            lines.add(lineResult[0]);
                            break;
                    }
                    i[0] = i[0] + 1;
                });

                String content = String.join("\n", lines);
                InputStream lobInputStream2 = new ByteArrayInputStream(content.getBytes(StandardCharsets.UTF_8));
                file.setContentResult(lobInputStream2);
                file.setSize(streamData.length / 1024);
                file.setSizeResult(content.getBytes(StandardCharsets.UTF_8).length / 1024);
                file.setFileNameResult("result.txt");
                file.setMediaTypeResult(file.getMediaType());
                file.setContent(streamForSaving);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
