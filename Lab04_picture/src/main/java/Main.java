
import io.indico.Indico;
//import io.indico.api.IndicoResult;
//import io.indico.api.BatchIndicoResult;
import java.util.List;

public class Main {
    // single example
    Indico indico = new Indico("abcbf277a75b62b7271ea0003fc17657");
//    IndicoResult single = indico.imageFeatures.predict(
//            "<IMAGE>"
//    );
//    Double result = single.getImageFeatures();
//        System.out.println(result);

    // batch example
    String[] example = {
            "<IMAGE>",
            "<IMAGE>"
    };
//    BatchIndicoResult multiple = indico.imageFeatures.predict(example);
//    List<Double> results = multiple.getImageFeatures();
//        System.out.println(results);
}