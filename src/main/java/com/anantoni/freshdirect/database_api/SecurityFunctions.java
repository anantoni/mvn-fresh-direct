/*
 * To change this template, choose Tools " Templates
 * and open the template in the editor.
 */
package com.anantoni.freshdirect.database_api;

/**
 *
 * @author Lelouch
 */
public class SecurityFunctions {

    public String sanitizeUserInput(String input) {
//                String[] array = { "<", ">", "!doctype", "a", "abbr", "address", "applet", "area", "article", "aside", "audio", "b", "base", "bb", "bdo" ,"bgsound" , "blockquote", "body", "br",
//                "button", "canvas", "caption", "cite", "code", "col", "colgroup", "command", "datagrid", "datalist", "dd", "del", "details", "dialog" , "dfn", "div", "dl",
//                "dt", "dynsrc", "em", "embed", "eventsource", "fieldset", "figure", "footer", "form", "h1", "h2", "h3", "h4", "h5", "h6", "head", "header", "hr", "html", "i", "ilayer",
//                "iframe", "frame", "frameset", "img", "input", "ins", "kbd" , "label" , "layer" , "legend" , "li", "link" ,"lowsrc" , "mark", "map", "menu", "meta" ,"meter",
//                "nav", "noscript", "object", "ol", "optgroup", "option" ,"output", "p", "param", "pre", "progress", "q", "ruby", "rp" ,"rt", "samp", "script", "section", "select", "set", 
//                "small", "source", "src", "span", "strong", "style" , "sub", "sup", "table", "tbody", "td", "textarea", "tfoot", "th", "thead", "time", "title", "tr", "ul", "var", "video", "xml", 
//                "insert", "update", "delete", "from", "*", "<%", "%>" };

        String array = "<\\/?(!doctype|a|abbr|address|applet|area|article|aside|audio|b|base|bb|bdo|bgsound|blockquote|body|br|button|"
                + "canvas|caption|cite|code|col|colgroup|command|datagrid|datalist|dd|del|details|dialog|dfn|div|dl|"
                + "dt|dynsrc|em|embed|eventsource|fieldset|figure|footer|form|h1|h2|h3|h4|h5|h6|head|header|hr|html|i|ilayer|"
                + "iframe|frame|frameset|img|input|ins|kbd|label|layer|legend|li|link|lowsrc|mark|map|menu|meta|meter|nav|noscript|object|ol|"
                + "optgroup|option|output|p|param|pre|progress|q|ruby|rp|rt|samp|script|section|select|set|small|source|src|"
                + "span|strong|style|sub|sup|table|tbody|td|textarea|tfoot|th|thead|time|title|tr|ul|var|video|xml)[^>]*>/is";

        String simple_sanitize = "<html>";
        input.replaceAll(simple_sanitize, "");

        return input;

    }

    public int checkCredentialsValidity(String input) {
        String[] array = {"/", "\\", "<", ">", "*", "^", "%", "#", "!", "~", "`", ".", ";", ":", "{", "}", "[", "]", "(", ")", "+", ",", "&"};

        for (String array1 : array) {
            if (input.contains(array1)) {
                return -1;
            }
        }
        return 0;
    }

    public int checkEmailValidity(String input) {
        String[] array = {"/", "\\", "<", ">", "*", "^", "%", "#", "!", "~", "`", ";", ":", "{", "}", "[", "]", "(", ")", "+", "=", ",", "$", "&", "?"};

        for (String array1 : array) {
            if (input.contains(array1)) {
                return -1;
            }
        }
        return 0;
    }

    public int checkInterestsValidity(String input) {
        String[] array = {"/", "\\", "<", ">", "*", "^", "%", "#", "!", "~", "`", ".", ";", ":", "{", "}", "[", "]", "(", ")",};

        for (String array1 : array) {
            if (input.contains(array1)) {
                return -1;
            }
        }
        return 0;
    }

}
