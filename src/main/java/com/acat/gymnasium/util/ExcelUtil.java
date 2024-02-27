package com.acat.gymnasium.util;

import com.acat.gymnasium.controller.bo.GymnasiumFitnessUserBo;
import com.acat.gymnasium.controller.bo.GymnasiumOperatorRecordBo;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class ExcelUtil {

    public static void convertExcelByMapByOperator(Map<String, List<GymnasiumOperatorRecordBo>> gymnasiumOperatorRecordBoListMap, String filePath) {
        if (gymnasiumOperatorRecordBoListMap == null) {
            return;
        }

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet;
        for (Map.Entry<String, List<GymnasiumOperatorRecordBo>> entry : gymnasiumOperatorRecordBoListMap.entrySet()) {
            //行号
            int rowNum = 0;
            //列号
            int colNum = 0;
            sheet = workbook.createSheet(entry.getKey());
            Row headerRow = sheet.createRow(rowNum++);
            headerRow.createCell(colNum).setCellValue("id");
            headerRow.createCell(colNum+1).setCellValue("会员姓名");
            headerRow.createCell(colNum+2).setCellValue("操作");
            headerRow.createCell(colNum+3).setCellValue("开始时间");
            headerRow.createCell(colNum+4).setCellValue("更新时间");

            List<GymnasiumOperatorRecordBo> gymnasiumOperatorRecordBoList = entry.getValue();
            for (GymnasiumOperatorRecordBo gymnasiumOperatorRecordBo : gymnasiumOperatorRecordBoList) {
                Row dataRow = sheet.createRow(rowNum++);
                dataRow.createCell(colNum++).setCellValue(gymnasiumOperatorRecordBo.getId());
                dataRow.createCell(colNum++).setCellValue(gymnasiumOperatorRecordBo.getUserName());
                dataRow.createCell(colNum++).setCellValue(gymnasiumOperatorRecordBo.getOperator());
                dataRow.createCell(colNum++).setCellValue(gymnasiumOperatorRecordBo.getCreateTime());
                dataRow.createCell(colNum++).setCellValue(gymnasiumOperatorRecordBo.getUpdateTime());
                colNum = 0;
            }
            colNum = 0;
        }

        try {
            FileOutputStream outputStream = new FileOutputStream(filePath);
            workbook.write(outputStream);
        }catch (IOException e) {

        }
    }

    public static void convertExcelByMapByFitnessUser(Map<String, List<GymnasiumFitnessUserBo>> gymnasiumFitnessUserBoListListMap, String filePath) {
        if (gymnasiumFitnessUserBoListListMap == null) {
            return;
        }

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet;
        for (Map.Entry<String, List<GymnasiumFitnessUserBo>> entry : gymnasiumFitnessUserBoListListMap.entrySet()) {
            //行号
            int rowNum = 0;
            //列号
            int colNum = 0;
            sheet = workbook.createSheet(entry.getKey());
            Row headerRow = sheet.createRow(rowNum++);
            headerRow.createCell(colNum).setCellValue("id");
            headerRow.createCell(colNum+1).setCellValue("会员姓名");
            headerRow.createCell(colNum+2).setCellValue("会员性别");
            headerRow.createCell(colNum+3).setCellValue("手机号");
            headerRow.createCell(colNum+4).setCellValue("教练ID");
            headerRow.createCell(colNum+5).setCellValue("教练姓名");
            headerRow.createCell(colNum+6).setCellValue("当前课程");
            headerRow.createCell(colNum+7).setCellValue("开始时间");
            headerRow.createCell(colNum+8).setCellValue("更新时间");

            List<GymnasiumFitnessUserBo> GymnasiumFitnessUserBoList = entry.getValue();
            for (GymnasiumFitnessUserBo gymnasiumFitnessUserBo : GymnasiumFitnessUserBoList) {
                Row dataRow = sheet.createRow(rowNum++);
                dataRow.createCell(colNum++).setCellValue(gymnasiumFitnessUserBo.getId());
                dataRow.createCell(colNum++).setCellValue(gymnasiumFitnessUserBo.getUsername());
                dataRow.createCell(colNum++).setCellValue(gymnasiumFitnessUserBo.getGender());
                dataRow.createCell(colNum++).setCellValue(gymnasiumFitnessUserBo.getPhone());
                dataRow.createCell(colNum++).setCellValue(gymnasiumFitnessUserBo.getTrainerId());
                dataRow.createCell(colNum++).setCellValue(gymnasiumFitnessUserBo.getTrainerName());
                dataRow.createCell(colNum++).setCellValue(gymnasiumFitnessUserBo.getCurrentCourse());
                dataRow.createCell(colNum++).setCellValue(gymnasiumFitnessUserBo.getCreateTime());
                dataRow.createCell(colNum++).setCellValue(gymnasiumFitnessUserBo.getUpdateTime());
                colNum = 0;
            }
            colNum = 0;
        }

        try {
            FileOutputStream outputStream = new FileOutputStream(filePath);
            workbook.write(outputStream);
        }catch (IOException e) {

        }
    }
}
