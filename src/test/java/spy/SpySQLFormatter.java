/*
 * Copyright 2018 skrymets.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package spy;

import com.p6spy.engine.common.P6Util;
import com.p6spy.engine.spy.P6SpyOptions;
import com.p6spy.engine.spy.appender.MessageFormattingStrategy;
import com.p6spy.engine.spy.appender.SingleLineFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.hibernate.engine.jdbc.internal.BasicFormatterImpl;
import org.hibernate.engine.jdbc.internal.Formatter;

/**
 *
 * @author skrymets
 */
public class SpySQLFormatter implements MessageFormattingStrategy {

    public static final String CONNECTION_ID = "%(connectionId)";
    public static final String CURRENT_TIME = "%(currentTime)";
    public static final String EXECUTION_TIME = "%(executionTime)";
    public static final String CATEGORY = "%(category)";
    public static final String EFFECTIVE_SQL = "%(effectiveSql)";
    public static final String EFFECTIVE_SQL_SINGLELINE = "%(effectiveSqlSingleLine)";
    public static final String SQL = "%(sql)";
    public static final String SQL_SINGLE_LINE = "%(sqlSingleLine)";

    private static final Formatter HB_FORMATTER = new BasicFormatterImpl();
    private static final MessageFormattingStrategy FALLBACK_FORMATTING = new SingleLineFormat();

    @Override
    public String formatMessage(int connectionId, String now, long elapsed, String category, String prepared, String sql) {
        if (sql.isEmpty()) {
            return "";
        }

        String customLogMessageFormat = P6SpyOptions.getActiveInstance().getCustomLogMessageFormat();

        if (customLogMessageFormat == null) {
            // Someone forgot to configure customLogMessageFormat: fall back to built-in
            return FALLBACK_FORMATTING.formatMessage(connectionId, now, elapsed, category, prepared, sql);
        }

        return customLogMessageFormat
                .replaceAll(Pattern.quote(CONNECTION_ID), Integer.toString(connectionId))
                .replaceAll(Pattern.quote(CURRENT_TIME), now)
                .replaceAll(Pattern.quote(EXECUTION_TIME), Long.toString(elapsed))
                .replaceAll(Pattern.quote(CATEGORY), category)
                .replaceAll(Pattern.quote(EFFECTIVE_SQL), Matcher.quoteReplacement(HB_FORMATTER.format(sql)))
                .replaceAll(Pattern.quote(EFFECTIVE_SQL_SINGLELINE), Matcher.quoteReplacement(P6Util.singleLine(prepared)))
                .replaceAll(Pattern.quote(SQL), Matcher.quoteReplacement(HB_FORMATTER.format(sql)))
                .replaceAll(Pattern.quote(SQL_SINGLE_LINE), Matcher.quoteReplacement(P6Util.singleLine(sql)));
    }

}
