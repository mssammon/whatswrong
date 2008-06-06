package whatswrong.io;

import whatswrong.NLPInstance;

import java.util.List;

/**
 * @author Sebastian Riedel
 */

public class CoNLL2000 implements CoNLLProcessor {

  public static final String name = "2000";


  public String toString() {
    return name;
  }

  public NLPInstance create(List<? extends List<String>> rows) {

    NLPInstance instance = new NLPInstance();
    int index = 0;
    for (List<String> row : rows) {
      String chunk = row.get(2);
      instance.addToken().
        addProperty("Word", row.get(0)).
        addProperty("Index", String.valueOf(index));

      instance.addSpan(index, index, row.get(1), "pos");
      instance.addSpan(index, index, chunk, "chunk (BIO)");
      ++index;
    }

    CoNLLFormat.extractSpan00(rows, 2, "chunk", instance);

    return instance;
  }


  public NLPInstance createOpen(List<? extends List<String>> rows) {
    return null;
  }

  public boolean supportsOpen() {
    return false;
  }
}