/**
 * 
 */
package jp.co.gzx.vo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


/**
 * 
 * @author huzl
 */
public final class ConstantsCreator {

    private String name;
    
    private FileWriter fileWriter;
    
    private StringBuffer fieldBuffer;
    
    private StringBuffer methodBuffer;
    
    private StringBuffer toStringBuffer;
    
    private StringBuffer toEqualsBuffer;
    
    private StringBuffer hashCodeBuffer;
    
    private StringBuffer fileEndBuffer;
    
    /**
     * コンストラクタ
     */
    public ConstantsCreator(String name) throws IOException {
        this.name = name;
        
        fileWriter = new FileWriter(name + ".java");

        fieldBuffer = new StringBuffer();
        methodBuffer = new StringBuffer();
        toStringBuffer = new StringBuffer();
        toEqualsBuffer = new StringBuffer();
        hashCodeBuffer = new StringBuffer();
        fileEndBuffer = new StringBuffer();
    }

    /**
     * クラスファイルを作成する。
     */
    public void createClass() throws IOException {
        // write header info
        outputClassHeader(name);
        
        // SerialVersionUIDを追加する。
        //appendSerialVersionUID();
        appendToStringHead();
        
        appendToEqualsHead();
        
        BufferedReader reader = new BufferedReader(new FileReader(name + ".vo"));
        String dataLine = reader.readLine();
        while (dataLine != null) {
            
            if (dataLine.trim().length() > 0) {
                String[] items = dataLine.split(",");
                
                String name = items[0];
                String type = items[1];
                String fieldName = items[2];
                String fieldValue = items[3];
                //String methodName = null;
                //if (items.length == 4) {
                  //methodName = items[3];
                //} else {
                  //String firstWord = fieldName.substring(0, 1);
                  //methodName = fieldName.replaceFirst(firstWord, firstWord.toUpperCase());
                //}
                
                
                appendField(name, type, fieldName, fieldValue);
                //appendMethod(name, type, fieldName, methodName);
                appendToString(name, fieldName);
                
                appendToEquals(name, fieldName);
                
            }
            dataLine = reader.readLine();
            
        }
        
        appendToStringTrailer();
        appendEqualsTrailer();
        outputClassFooter();

        fileWriter.write(fieldBuffer.toString());
        
        appendConstructor();
        
        fileWriter.write(methodBuffer.toString());
        
        fileWriter.write(toStringBuffer.toString());
        
        fileWriter.write(toEqualsBuffer.toString());
        
        appendOthers();
        
        
        fileWriter.close();
        
    }
    
    private void appendToEquals(String name, String fieldName) throws IOException {
//      toEqualsBuffer.append("\r\n").append("            && ObjectUtils.equals(this.").append(fieldName).append(", target.").append(fieldName).append(")");
    }
    
    private void appendToEqualsHead() {
//      toEqualsBuffer.append("    /**").append("\r\n");
//      toEqualsBuffer.append("     * このオブジェクトと他のオブジェクトが等しいかどうかを示します。").append("\r\n");
//      toEqualsBuffer.append("     * ").append("\r\n");
//      toEqualsBuffer.append("     * @param obj 比較対象の参照オブジェクト").append("\r\n");
//      toEqualsBuffer.append("     * @return 引数に指定されたオブジェクトとこのオブジェクトが等しい場合は true、").append("\r\n");
//      toEqualsBuffer.append("     *         そうでない場合は false").append("\r\n");
//        
//      toEqualsBuffer.append("     *").append("\r\n");
//      toEqualsBuffer.append("     * @see java.lang.Object\\#equals(java.lang.Object)").append("\r\n");
//
//      toEqualsBuffer.append("     */").append("\r\n");
//        toEqualsBuffer.append("    @Override").append("\r\n");
//        toEqualsBuffer.append("    public boolean equals(Object obj) {").append("\r\n").append("\r\n");
//        toEqualsBuffer.append("        if (!(obj instanceof ").append(this.name).append(")) {").append("\r\n");
//        toEqualsBuffer.append("            return false;").append("\r\n");
//        toEqualsBuffer.append("        }").append("\r\n");
//        toEqualsBuffer.append("        ").append(this.name).append(" target = (").append(this.name).append(") obj;").append("\r\n");
//        toEqualsBuffer.append("\r\n");
//        toEqualsBuffer.append("        return (super.equals(target)");
        
        
//        toStringBuffer.append("        // クラス名").append("\r\n");
//        toStringBuffer.append("        final String clsName = this.getClass().getName();").append("\r\n");
//        toStringBuffer.append("        buffer.append(\"#class::\").append(clsName).append(DELIM);").append("\r\n\r\n");
    }
    
    private void appendEqualsTrailer() {
//      toEqualsBuffer.append(");").append("\r\n");
//      toEqualsBuffer.append("    }").append("\r\n\r\n");
    }
    
    private void outputClassHeader(String name) throws IOException {
      fieldBuffer.append("public class "); 
      fieldBuffer.append(name); 
      fieldBuffer.append(" {\r\n\r\n"); 
    }

    private void outputClassFooter() throws IOException {
      fieldBuffer.append("}"); 
    }
    
    private void appendSerialVersionUID() throws IOException {
        fieldBuffer.append("    /** serialVersionUID */").append("\r\n"); 
//        fieldBuffer.append("     * serialVersionUID").append("\r\n");
//        fieldBuffer.append("     */").append("\r\n");
        fieldBuffer.append("    private static final long serialVersionUID = 1L;").append("\r\n\r\n");
    }
    
    /**
     * 
     * @param name
     * @param type
     * @param fieldName
     * @param fieldValue
     */
    private void appendField(String name, String type, String fieldName, String fieldValue) {
       fieldBuffer.append("    /**"); 
       fieldBuffer.append(" ").append(name);
       fieldBuffer.append(" */").append("\r\n");
       fieldBuffer.append("    public static final ").append(type).append(" ").append(fieldName);
       if ("int".equals(type)) {
           fieldBuffer.append(" = 0;\r\n\r\n");
       } else if ("float".equals(type)) {
           fieldBuffer.append(" = 0;\r\n\r\n");
       } else if ("double".equals(type)) {
           fieldBuffer.append(" = 0;\r\n\r\n");
       } else if ("long".equals(type)) {
           fieldBuffer.append(" = 0;\r\n\r\n");
       } else if ("char".equals(type)) {
           fieldBuffer.append(" = 0;\r\n\r\n");
       } else {
           fieldBuffer.append(" = ");
       }
       fieldBuffer.append("\"").append(fieldValue).append("\";\r\n\r\n");
    }
    
    /**
     * 
     * @param name
     * @param codeName
     * @param methodName
     * @param methodParam
     * @param codeValue
     */
    private void appendMethod(String name, String type, String fieldName, String methodName) {
        methodBuffer.append("    /**").append("\r\n");
        methodBuffer.append("     * ").append(name).append("を取得します。").append("\r\n");
        methodBuffer.append("     *").append("\r\n");
        methodBuffer.append("     * @return ").append(name).append("\r\n");
        methodBuffer.append("     */").append("\r\n");
        methodBuffer.append("    public final ").append(type).append(" get").append(methodName).append("() {").append("\r\n");
        methodBuffer.append("        return this.").append(fieldName).append(";\r\n");
        methodBuffer.append("    }\r\n\r\n");
        
        methodBuffer.append("    /**").append("\r\n");
        methodBuffer.append("     * ").append(name).append("を設定します。").append("\r\n");
        methodBuffer.append("     *").append("\r\n");
        methodBuffer.append("     * @param ").append(fieldName).append(" ").append(name).append("\r\n");
        methodBuffer.append("     */").append("\r\n");
        methodBuffer.append("    public final void set").append(methodName).append("(").append(type).append(" ").append(fieldName).append(") ").append("{\r\n");
        methodBuffer.append("        this.").append(fieldName).append(" = ").append(fieldName).append(";\r\n");
        methodBuffer.append("    }\r\n\r\n");
    }
    
    private void appendToString(String name, String fieldName) throws IOException {
//        toStringBuffer.append("        // ").append(name).append("\r\n");
////        toStringBuffer.append("        buffer.append(\"[").append(fieldName).append("::").append("\").append(").append(fieldName).append(").append(\"]\");").append("\r\n");
//        toStringBuffer.append("        builder.append(\"[").append(name).append(" =").append("\");").append("\r\n");
//        toStringBuffer.append("        builder.append(this.").append(fieldName).append(");").append("\r\n");
//        toStringBuffer.append("        builder.append(\"]\");").append("\r\n").append("\r\n");
    }
    
    private void appendToStringHead() {
//        toStringBuffer.append("    /**").append("\r\n");
//        toStringBuffer.append("     * 文字列形式オブジェクト内容を取得する。").append("\r\n");
//        toStringBuffer.append("     * ").append("\r\n");
//        toStringBuffer.append("     * @return 文字列形式オブジェクト内容").append("\r\n");
//        toStringBuffer.append("     */").append("\r\n");
//        toStringBuffer.append("    @Override").append("\r\n");
//        toStringBuffer.append("    public String toString() {").append("\r\n\r\n");
//        toStringBuffer.append("        StringBuilder builder = new StringBuilder();").append("\r\n");
//        toStringBuffer.append("").append("\r\n");
        
        
//        toStringBuffer.append("        // クラス名").append("\r\n");
//        toStringBuffer.append("        final String clsName = this.getClass().getName();").append("\r\n");
//        toStringBuffer.append("        buffer.append(\"#class::\").append(clsName).append(DELIM);").append("\r\n\r\n");
    }

    private void appendToStringTrailer() {
//        toStringBuffer.append("        return builder.toString();").append("\r\n");
//        toStringBuffer.append("    }").append("\r\n\r\n");
    }
    
    private void appendConstructor() throws IOException {
//        fileWriter.write("    /**" + "\r\n");
//        fileWriter.write("     * コンストラクタ" + "\r\n");
//        fileWriter.write("     */" + "\r\n");
//        fileWriter.write("    public ");
//        fileWriter.write(this.name);
//        fileWriter.write("() {" + "\r\n");
//        fileWriter.write("        super();" + "\r\n");
//        fileWriter.write("    }" + "\r\n\r\n");
    }
    
    private void appendOthers() throws IOException {

//      hashCodeBuffer.append("    /**").append("\r\n");
//      hashCodeBuffer.append("     * オブジェクトのハッシュコード値を返します。").append("\r\n");
//      hashCodeBuffer.append("     * ").append("\r\n");
//      hashCodeBuffer.append("     * @return このオブジェクトのハッシュコード値").append("\r\n");
//      hashCodeBuffer.append("     * @see java.lang.Object\\#hashCode()").append("\r\n");
//      hashCodeBuffer.append("     */").append("\r\n");
//      hashCodeBuffer.append("    @Override").append("\r\n");
//      hashCodeBuffer.append("    public int hashCode() {").append("\r\n");
//      hashCodeBuffer.append("        return (toString().hashCode());").append("\r\n");
//      hashCodeBuffer.append("    }").append("\r\n");
//      
//      fileEndBuffer.append("}").append("\r\n");
//      
//        fileWriter.write(hashCodeBuffer.toString());
//        fileWriter.write(fileEndBuffer.toString());
        
    }
    
    /**
     * 実行メソッド
     * 
     * @param args パラメータ
     */
    public static void main(String[] args) {
        try {
            String name = "ImSubIfConstants";
            ConstantsCreator creator = new ConstantsCreator(name);
            creator.createClass();
            System.out.println("good!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
