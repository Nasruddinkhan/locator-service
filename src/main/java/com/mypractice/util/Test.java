package com.mypractice.util;

import org.apache.commons.collections4.MultiValuedMap;
import org.apache.commons.collections4.multimap.HashSetValuedHashMap;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


 public class Test {
    public static String readFile(String path, Charset encoding) throws IOException {
        return Files.readString(Paths.get(path), encoding);
    }
    public static void main(String[] args) {
//        String filePath = "classpath:atm.json";
//
//        String content = null;
//        try {
//            content = readFile(filePath, StandardCharsets.UTF_8);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        System.out.println(content);
        StringBuilder charTypeN = new StringBuilder();
        for (var ch = '0'; ch <= '9'; ch++) charTypeN.append(ch);
        System.out.println(charTypeN);
    }

    public static List<ValuePropModel> readValueProps(File file) throws Exception {
        List<ValuePropModel> valuePropModelList = new ArrayList<>();
        try (FileInputStream fis = new FileInputStream(file);
             InputStreamReader isr = new InputStreamReader(fis, "UNICODE");
             BufferedReader br = new BufferedReader(isr)
        ) {
            String line = br.readLine();
            int i = 0;
            while(line != null) {
                if (i > 0) {
                    String[] columns = line.split("\t");

                    String promoValue = columns[0];
                    String[] months = columns[1].split(",");
                    String vpid = columns[2];
                    String language = columns[3];
                    for (int month_index = 0; month_index < months.length; month_index++) {
                        MultiValuedMap<String, String> props = new HashSetValuedHashMap<String, String>();
                        String month = months[month_index].replaceAll("\"", "").strip();
                        for (int index = 4; index < columns.length; index++) {
                            String prop = columns[index];
                            if (prop.contains("#lfm")) {
                                prop = prop.replaceAll("#lfm", month);
                            }
                            props.put("prop" + (index - 3), prop);
                        }
                        String vpid_for_model = "";
                        if (month.length() > 0 && !month.equals("0"))
                            vpid_for_model = month + "MM_" + vpid;
                        else
                            vpid_for_model = vpid;
                        ValuePropModel model = new ValuePropModel();
                        model.setPromoValue(promoValue);
                        model.setVpid(vpid_for_model);
                        model.setLanguage(language);
                        model.setProps(props);
                        valuePropModelList.add(model);
                    }
                }
                line = br.readLine();
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return valuePropModelList;
    }
}
