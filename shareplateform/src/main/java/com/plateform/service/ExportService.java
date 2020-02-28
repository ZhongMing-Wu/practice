package com.plateform.service;

import javax.servlet.ServletOutputStream;

public interface ExportService {

    public void export(String[] titles, ServletOutputStream out, int requestType);
}
