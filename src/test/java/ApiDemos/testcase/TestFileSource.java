package ApiDemos.testcase;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SequenceWriter;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.xueqiu.app.testcase.TestSearch;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Test;

import javax.naming.Name;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

public class TestFileSource {

    public String name = "tester";
    public int age = 2;
    public Object[][] arr= {{1,2,3,},{"a","b","c"}};
    public HashMap<String,Object> map = new HashMap<String, Object>(){
        {
        put("name","tester");
        put("sex","男");
        }
    };



    @Test
    void readFile() throws IOException {
        //yaml中读取数据
        System.out.println(this.getClass().getResource("/"));
        System.out.println(this.getClass().getResource("/app/Test.yaml"));
        System.out.println(FileUtils.readFileToString(new File(this.getClass().getResource("/app/Test.yaml").getPath()),"utf8"));
        //打印类的信息，打印结果：ApiDemos.testcase.TestFileSource
        //把.换成/就可以作为类的数据文件的地址，便可以做类的数据驱动了,将文件的地址和类的地址保持一致即可
        System.out.println(this.getClass().getName());
        System.out.println(this.getClass().getCanonicalName());
    }

    @Test
    void writeJson() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(new File("..\\demo.json"),TestFileSource.class);
    }

    @Test
    void readJson() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        TestFileSource testFileSource = mapper.readValue(TestFileSource.class.getResourceAsStream("/demo.json"), TestFileSource.class);
        System.out.println(testFileSource);
        System.out.println(testFileSource.age);
    }

    @Test
    void prettyPrintJson() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        // pretty print
        String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(new TestFileSource());
        System.out.println(json);
    }

    @Test
    void writeYaml() throws IOException {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        JsonNode jsonNode = mapper.readTree("name:allen");
        // modify root here
        FileOutputStream fos = new FileOutputStream(new File("..\\demo2.yaml"));
        SequenceWriter sw = mapper.writerWithDefaultPrettyPrinter().writeValues(fos);
        sw.write(jsonNode);
//        mapper.writeValue(new File("demo2.yaml"),TestFileSource.class);
    }

    @Test
    void readYaml() throws IOException {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        TestFileSource testFileSource = mapper.readValue(TestFileSource.class.getResourceAsStream("/demo2.yaml"), TestFileSource.class);
        System.out.println(testFileSource);
        System.out.println(testFileSource.age);
    }



    @Test
    void test(){
        String[] split = TestSearch.class.getCanonicalName().split("app");
        System.out.println(TestSearch.class.getCanonicalName());
        System.out.println("/com.xueqiu.app"+split[1].replace(".","/"));
        /*for (int i = 0; i < split.length; i++) {
            System.out.println(split[i]);
        }*/
//        String path1 = "/com.xueqiu.app" + Arrays.copyOfRange(split,3,split.length).toString() + ".yaml";
//        System.out.println(path1);
    }
}
