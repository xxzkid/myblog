package org.xxz.myblog.util;

import org.pegdown.Extensions;
import org.pegdown.PegDownProcessor;

/**
 * @author tt
 */
public final class MarkdownUtil {

    private static PegDownProcessor processor = new PegDownProcessor(
            Extensions.SMARTS |
            Extensions.QUOTES |
            Extensions.ABBREVIATIONS |
            Extensions.HARDWRAPS |
            Extensions.AUTOLINKS |
            Extensions.TABLES |
            Extensions.FENCED_CODE_BLOCKS |
            Extensions.DEFINITIONS |
            Extensions.STRIKETHROUGH |
            Extensions.TASKLISTITEMS);

    private MarkdownUtil() {}

    public static String markdown2Html(String markdownSource) {
        return processor.markdownToHtml(markdownSource);
    }

}
