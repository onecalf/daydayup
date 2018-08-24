package com.onecalf.nb.colorchecker;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ColorChecker {
    private List<ColorBean> mTotalColorList;
    private String mColorFilePath;

    public ColorChecker(){
        mTotalColorList = new ArrayList<>();
    }

    public void setColorFilePath(String colorFilePath) {
        this.mColorFilePath = colorFilePath;
    }

    public void printRepeatColor(){
        List<RepeatColorBean> repeatColorList = findRepeatColorList();
        for (RepeatColorBean repeatColorBean : repeatColorList ){
            System.out.println("颜色值为= " + repeatColorBean.colorValue);
            for (String colorKey : repeatColorBean.colorKeyList){
                System.out.println("\t\t重复的key为= " + colorKey);
            }
        }
    }

    private List<RepeatColorBean> findRepeatColorList(){
        parseColorFile();
        if(mTotalColorList.isEmpty()){
            return null;
        }

        //1 排重value
        Set<String> colorKeySet = new HashSet<>();
        for (int i =0;i < mTotalColorList.size();i++){
            ColorBean colorBean = mTotalColorList.get(i);
            colorKeySet.add(colorBean.colorValue);
        }

        //2 查找重复的key
        List<RepeatColorBean> repeatColorBeanList = new ArrayList<>();
        for (String colorValue : colorKeySet){
            RepeatColorBean repeatColorBean = new RepeatColorBean();
            repeatColorBean.colorValue = colorValue;
            repeatColorBean.colorKeyList = getColorKeyList(colorValue);

            if(repeatColorBean.colorKeyList != null && repeatColorBean.colorKeyList.size() > 1){
                repeatColorBeanList.add(repeatColorBean);
            }
        }

        return repeatColorBeanList;
    }

    private List<String> getColorKeyList(String colorValue) {
        if(mTotalColorList.isEmpty() || colorValue == null){
            return null;
        }

        List<String> colorKeyList = new ArrayList<>();
        for (int i =0;i < mTotalColorList.size();i++){
            ColorBean colorBean = mTotalColorList.get(i);

            if(colorValue.equalsIgnoreCase(colorBean.colorValue)){
                colorKeyList.add(colorBean.colorName);
            }
        }

        return colorKeyList;
    }

    private void parseColorFile(){
        if(!mTotalColorList.isEmpty()){
            mTotalColorList.clear();
        }

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db;
        try {
            db = dbf.newDocumentBuilder();
            Document document = db.parse(mColorFilePath);
            NodeList nodeList = document.getElementsByTagName("color");
            for (int i = 0; i < nodeList.getLength();i++){
                Node node = nodeList.item(i);
                NamedNodeMap attributes = node.getAttributes();
                for (int j = 0 ;j < attributes.getLength(); j++){
                    String name = attributes.item(j).getNodeValue();
                    String value = node.getTextContent();

                    mTotalColorList.add(new ColorBean(name,value));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
