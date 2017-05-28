package com.pr.expense.service;

import com.pr.commons.csv.ParseResult;
import com.pr.commons.dto.Message;
import com.pr.expense.csv.ActivityCsv;

/**
 * Created by jhanay on 1/15/2017.
 */
public interface CSVIOService {


    String upload(String csvData);

	ParseResult<ActivityCsv> validate(String data);

	Message uploadCategory(String cavData);
}
