package io.cdap.wrangler.directives;

import io.cdap.wrangler.api.Row;
import io.cdap.wrangler.api.parser.ByteSize;
import io.cdap.wrangler.api.parser.TimeDuration;
import io.cdap.wrangler.api.ExecutorContext;
import io.cdap.wrangler.api.Directive;

import java.util.Collections;
import java.util.List;

public class AggregateStats implements Directive {
    private String srcSizeCol = "size_col";
    private String srcTimeCol = "duration_col";
    private String targetSizeCol = "result_size";
    private String targetTimeCol = "result_time";

    @Override
    public List<Row> execute(List<Row> rows, ExecutorContext ctx) {
        long totalBytes = 0;
        long totalMillis = 0;

        for (Row row : rows) {
            totalBytes += new ByteSize((String) row.getValue(srcSizeCol)).getBytes();
            totalMillis += new TimeDuration((String) row.getValue(srcTimeCol)).getMillis();
        }

        Row result = new Row();
        result.add(targetSizeCol, totalBytes / (1024.0 * 1024));  // MB
        result.add(targetTimeCol, totalMillis / 1000.0);          // seconds

        return Collections.singletonList(result);
    }
}
