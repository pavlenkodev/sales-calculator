package ru.dream_store_kzn.report_parser.rest;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import ru.dream_store_kzn.report_parser.model.ProfitModel;

@RequestMapping("/api")
public interface ProfitRest {

    @PostMapping("/calculate")
    ProfitModel calculate(@RequestParam(value = "salesReport") MultipartFile salesReport,
                          @RequestParam(value = "paidStorageReport", required = false) MultipartFile paidStorageReport);
}
