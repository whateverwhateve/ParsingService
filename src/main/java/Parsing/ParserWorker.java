package Parsing;

import org.jsoup.nodes.Document;
import java.io.IOException;
import java.util.ArrayList;

public class ParserWorker<T> {
    protected Parser<T> parser;
    protected ParserSettings parserSettings;
    boolean isActive;
    public ArrayList<OnNewDataHandler> onNewDataList = new ArrayList<>();
    public ArrayList<OnCompleted> onCompletedList = new ArrayList<>();

    public Parser<T> getParser() {
        return parser;
    }

    public void setParser(Parser parser) {
        this.parser = parser;
    }

    public void setParserSettings(ParserSettings parserSettings) {
        this.parserSettings = parserSettings;
    }

    public void Start() throws IOException {
        isActive = true;
        Worker();
    }

    public void Abort() {
        isActive = false;
    }

    private void Worker() throws IOException {
        for (int i = parserSettings.getStartPoint(); i <= parserSettings.getEndPoint(); ++i) {
            if (!isActive) {
                onCompletedList.get(0).OnCompleted(this);
                return;
            }
            HtmlLoader loader;
            if (ParserSettings.PREFIX != null)
                loader = new HtmlLoader(ParserSettings.BASE_URL + "/" + ParserSettings.PREFIX);
            else
                loader = new HtmlLoader(ParserSettings.BASE_URL);
            Document document = loader.GetSourceByPageId(i);
            T result = parser.Parse(document);
            onNewDataList.get(0).OnNewData(this, result);
        }
        onCompletedList.get(0).OnCompleted(this);
        isActive = false;
    }
}