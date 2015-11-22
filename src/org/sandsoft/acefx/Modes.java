/*
 * Copyright 2015 Sudipto Chandra.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http:    //www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.sandsoft.acefx;

import org.sandsoft.acefx.model.ModeData;

/**
 * Contains some pre-defined syntax highlighting modes for ace editor.
 *
 * @author Sudipto Chandra.
 */
public final class Modes {

    public static final ModeData ABAP = new ModeData("ABAP", "ace/mode/abap", "abap");
    public static final ModeData ABC = new ModeData("ABC", "ace/mode/abc", "abc");
    public static final ModeData ActionScript = new ModeData("ActionScript", "ace/mode/actionscript", "as");
    public static final ModeData ADA = new ModeData("ADA", "ace/mode/ada", "ada|adb");
    public static final ModeData Apache_Conf = new ModeData("Apache Conf", "ace/mode/apache_conf", "^htaccess|^htgroups|^htpasswd|^conf|htaccess|htgroups|htpasswd");
    public static final ModeData AsciiDoc = new ModeData("AsciiDoc", "ace/mode/asciidoc", "asciidoc|adoc");
    public static final ModeData Assembly_x86 = new ModeData("Assembly x86", "ace/mode/assembly_x86", "asm|a");
    public static final ModeData AutoHotKey = new ModeData("AutoHotKey", "ace/mode/autohotkey", "ahk");
    public static final ModeData BatchFile = new ModeData("BatchFile", "ace/mode/batchfile", "bat|cmd");
    public static final ModeData C_Cpp = new ModeData("C and C++", "ace/mode/c_cpp", "cpp|c|cc|cxx|h|hh|hpp|ino");
    public static final ModeData C9Search = new ModeData("C9Search", "ace/mode/c9search", "c9search_results");
    public static final ModeData Cirru = new ModeData("Cirru", "ace/mode/cirru", "cirru|cr");
    public static final ModeData Clojure = new ModeData("Clojure", "ace/mode/clojure", "clj|cljs");
    public static final ModeData Cobol = new ModeData("Cobol", "ace/mode/cobol", "CBL|COB");
    public static final ModeData coffee = new ModeData("CoffeeScript", "ace/mode/coffee", "coffee|cf|cson|^Cakefile");
    public static final ModeData ColdFusion = new ModeData("ColdFusion", "ace/mode/coldfusion", "cfm");
    public static final ModeData CSharp = new ModeData("C#", "ace/mode/csharp", "cs");
    public static final ModeData CSS = new ModeData("CSS", "ace/mode/css", "css");
    public static final ModeData Curly = new ModeData("Curly", "ace/mode/curly", "curly");
    public static final ModeData D = new ModeData("D", "ace/mode/d", "d|di");
    public static final ModeData Dart = new ModeData("Dart", "ace/mode/dart", "dart");
    public static final ModeData Diff = new ModeData("Diff", "ace/mode/diff", "diff|patch");
    public static final ModeData Dockerfile = new ModeData("Dockerfile", "ace/mode/dockerfile", "^Dockerfile");
    public static final ModeData Dot = new ModeData("Dot", "ace/mode/dot", "dot");
    public static final ModeData Dummy = new ModeData("Dummy", "ace/mode/dummy", "dummy");
    public static final ModeData DummySyntax = new ModeData("DummySyntax", "ace/mode/dummysyntax", "dummy");
    public static final ModeData Eiffel = new ModeData("Eiffel", "ace/mode/eiffel", "e|ge");
    public static final ModeData EJS = new ModeData("EJS", "ace/mode/ejs", "ejs");
    public static final ModeData Elixir = new ModeData("Elixir", "ace/mode/elixir", "ex|exs");
    public static final ModeData Elm = new ModeData("Elm", "ace/mode/elm", "elm");
    public static final ModeData Erlang = new ModeData("Erlang", "ace/mode/erlang", "erl|hrl");
    public static final ModeData Forth = new ModeData("Forth", "ace/mode/forth", "frt|fs|ldr");
    public static final ModeData FTL = new ModeData("FreeMarker", "ace/mode/ftl", "ftl");
    public static final ModeData Gcode = new ModeData("Gcode", "ace/mode/gcode", "gcode");
    public static final ModeData Gherkin = new ModeData("Gherkin", "ace/mode/gherkin", "feature");
    public static final ModeData Gitignore = new ModeData("Gitignore", "ace/mode/gitignore", "^.gitignore");
    public static final ModeData Glsl = new ModeData("Glsl", "ace/mode/glsl", "glsl|frag|vert");
    public static final ModeData golang = new ModeData("Go", "ace/mode/golang", "go");
    public static final ModeData Groovy = new ModeData("Groovy", "ace/mode/groovy", "groovy");
    public static final ModeData HAML = new ModeData("HAML", "ace/mode/haml", "haml");
    public static final ModeData Handlebars = new ModeData("Handlebars", "ace/mode/handlebars", "hbs|handlebars|tpl|mustache");
    public static final ModeData Haskell = new ModeData("Haskell", "ace/mode/haskell", "hs");
    public static final ModeData haXe = new ModeData("haXe", "ace/mode/haxe", "hx");
    public static final ModeData HTML = new ModeData("HTML", "ace/mode/html", "html|htm|xhtml");
    public static final ModeData HTML_Ruby = new ModeData("HTML (Ruby)", "ace/mode/html_ruby", "erb|rhtml|html.erb");
    public static final ModeData INI = new ModeData("INI", "ace/mode/ini", "ini|conf|cfg|prefs");
    public static final ModeData Io = new ModeData("Io", "ace/mode/io", "io");
    public static final ModeData Jack = new ModeData("Jack", "ace/mode/jack", "jack");
    public static final ModeData Jade = new ModeData("Jade", "ace/mode/jade", "jade");
    public static final ModeData Java = new ModeData("Java", "ace/mode/java", "java");
    public static final ModeData JavaScript = new ModeData("JavaScript", "ace/mode/javascript", "js|jsm");
    public static final ModeData JSON = new ModeData("JSON", "ace/mode/json", "json");
    public static final ModeData JSONiq = new ModeData("JSONiq", "ace/mode/jsoniq", "jq");
    public static final ModeData JSP = new ModeData("JSP", "ace/mode/jsp", "jsp");
    public static final ModeData JSX = new ModeData("JSX", "ace/mode/jsx", "jsx");
    public static final ModeData Julia = new ModeData("Julia", "ace/mode/julia", "jl");
    public static final ModeData LaTeX = new ModeData("LaTeX", "ace/mode/latex", "tex|latex|ltx|bib");
    public static final ModeData Lean = new ModeData("Lean", "ace/mode/lean", "lean|hlean");
    public static final ModeData LESS = new ModeData("LESS", "ace/mode/less", "less");
    public static final ModeData Liquid = new ModeData("Liquid", "ace/mode/liquid", "liquid");
    public static final ModeData Lisp = new ModeData("Lisp", "ace/mode/lisp", "lisp");
    public static final ModeData LiveScript = new ModeData("LiveScript", "ace/mode/livescript", "ls");
    public static final ModeData LogiQL = new ModeData("LogiQL", "ace/mode/logiql", "logic|lql");
    public static final ModeData LSL = new ModeData("LSL", "ace/mode/lsl", "lsl");
    public static final ModeData Lua = new ModeData("Lua", "ace/mode/lua", "lua");
    public static final ModeData LuaPage = new ModeData("LuaPage", "ace/mode/luapage", "lp");
    public static final ModeData Lucene = new ModeData("Lucene", "ace/mode/lucene", "lucene");
    public static final ModeData Makefile = new ModeData("Makefile", "ace/mode/makefile", "^Makefile|^GNUmakefile|^makefile|^OCamlMakefile|make");
    public static final ModeData Markdown = new ModeData("Markdown", "ace/mode/markdown", "md|markdown");
    public static final ModeData Mask = new ModeData("Mask", "ace/mode/mask", "mask");
    public static final ModeData MATLAB = new ModeData("MATLAB", "ace/mode/matlab", "matlab");
    public static final ModeData Maze = new ModeData("Maze", "ace/mode/maze", "mz");
    public static final ModeData MEL = new ModeData("MEL", "ace/mode/mel", "mel");
    public static final ModeData MUSHCode = new ModeData("MUSHCode", "ace/mode/mushcode", "mc|mush");
    public static final ModeData MySQL = new ModeData("MySQL", "ace/mode/mysql", "mysql");
    public static final ModeData Nix = new ModeData("Nix", "ace/mode/nix", "nix");
    public static final ModeData ObjectiveC = new ModeData("Objective-C", "ace/mode/objectivec", "m|mm");
    public static final ModeData OCaml = new ModeData("OCaml", "ace/mode/ocaml", "ml|mli");
    public static final ModeData Pascal = new ModeData("Pascal", "ace/mode/pascal", "pas|p");
    public static final ModeData Perl = new ModeData("Perl", "ace/mode/perl", "pl|pm");
    public static final ModeData pgSQL = new ModeData("pgSQL", "ace/mode/pgsql", "pgsql");
    public static final ModeData PHP = new ModeData("PHP", "ace/mode/php", "php|phtml|shtml|php3|php4|php5|phps|phpt|aw|ctp");
    public static final ModeData Powershell = new ModeData("Powershell", "ace/mode/powershell", "ps1");
    public static final ModeData Praat = new ModeData("Praat", "ace/mode/praat", "praat|praatscript|psc|proc");
    public static final ModeData Prolog = new ModeData("Prolog", "ace/mode/prolog", "plg|prolog");
    public static final ModeData Properties = new ModeData("Properties", "ace/mode/properties", "properties");
    public static final ModeData Protobuf = new ModeData("Protobuf", "ace/mode/protobuf", "proto");
    public static final ModeData Python = new ModeData("Python", "ace/mode/python", "py");
    public static final ModeData R = new ModeData("R", "ace/mode/r", "r");
    public static final ModeData RDoc = new ModeData("RDoc", "ace/mode/rdoc", "Rd");
    public static final ModeData RHTML = new ModeData("RHTML", "ace/mode/rhtml", "Rhtml");
    public static final ModeData Ruby = new ModeData("Ruby", "ace/mode/ruby", "rb|ru|gemspec|rake|^Guardfile|^Rakefile|^Gemfile");
    public static final ModeData Rust = new ModeData("Rust", "ace/mode/rust", "rs");
    public static final ModeData SASS = new ModeData("SASS", "ace/mode/sass", "sass");
    public static final ModeData SCAD = new ModeData("SCAD", "ace/mode/scad", "scad");
    public static final ModeData Scala = new ModeData("Scala", "ace/mode/scala", "scala");
    public static final ModeData Scheme = new ModeData("Scheme", "ace/mode/scheme", "scm|sm|rkt|oak|scheme");
    public static final ModeData SCSS = new ModeData("SCSS", "ace/mode/scss", "scss");
    public static final ModeData SH = new ModeData("SH", "ace/mode/sh", "sh|bash|^.bashrc");
    public static final ModeData SJS = new ModeData("SJS", "ace/mode/sjs", "sjs");
    public static final ModeData Smarty = new ModeData("Smarty", "ace/mode/smarty", "smarty|tpl");
    public static final ModeData snippets = new ModeData("snippets", "ace/mode/snippets", "snippets");
    public static final ModeData Soy_Template = new ModeData("Soy Template", "ace/mode/soy_template", "soy");
    public static final ModeData Space = new ModeData("Space", "ace/mode/space", "space");
    public static final ModeData SQL = new ModeData("SQL", "ace/mode/sql", "sql");
    public static final ModeData SQLServer = new ModeData("SQLServer", "ace/mode/sqlserver", "sqlserver");
    public static final ModeData Stylus = new ModeData("Stylus", "ace/mode/stylus", "styl|stylus");
    public static final ModeData SVG = new ModeData("SVG", "ace/mode/svg", "svg");
    public static final ModeData Swift = new ModeData("Swift", "ace/mode/swift", "swift");
    public static final ModeData Tcl = new ModeData("Tcl", "ace/mode/tcl", "tcl");
    public static final ModeData Tex = new ModeData("Tex", "ace/mode/tex", "tex");
    public static final ModeData Text = new ModeData("Text", "ace/mode/text", "txt");
    public static final ModeData Textile = new ModeData("Textile", "ace/mode/textile", "textile");
    public static final ModeData Toml = new ModeData("Toml", "ace/mode/toml", "toml");
    public static final ModeData Twig = new ModeData("Twig", "ace/mode/twig", "twig|swig");
    public static final ModeData Typescript = new ModeData("Typescript", "ace/mode/typescript", "ts|typescript|str");
    public static final ModeData Vala = new ModeData("Vala", "ace/mode/vala", "vala");
    public static final ModeData VBScript = new ModeData("VBScript", "ace/mode/vbscript", "vbs|vb");
    public static final ModeData Velocity = new ModeData("Velocity", "ace/mode/velocity", "vm");
    public static final ModeData Verilog = new ModeData("Verilog", "ace/mode/verilog", "v|vh|sv|svh");
    public static final ModeData VHDL = new ModeData("VHDL", "ace/mode/vhdl", "vhd|vhdl");
    public static final ModeData XML = new ModeData("XML", "ace/mode/xml", "xml|rdf|rss|wsdl|xslt|atom|mathml|mml|xul|xbl|xaml");
    public static final ModeData XQuery = new ModeData("XQuery", "ace/mode/xquery", "xq");
    public static final ModeData YAML = new ModeData("YAML", "ace/mode/yaml", "yaml|yml");
    public static final ModeData Django = new ModeData("Django", "ace/mode/django", "html");

    public static final ModeData[] SUPPORTED_MODES = {
        ABAP, ABC, ActionScript, ADA, Apache_Conf, AsciiDoc, Assembly_x86,
        AutoHotKey, BatchFile, C_Cpp, C9Search, Cirru, Clojure, Cobol, coffee,
        ColdFusion, CSharp, CSS, Curly, D, Dart, Diff, Dockerfile,
        Dot, Dummy, DummySyntax, Eiffel, EJS, Elixir, Elm, Erlang,
        Forth, FTL, Gcode, Gherkin, Gitignore, Glsl, golang, Groovy,
        HAML, Handlebars, Haskell, haXe, HTML, HTML_Ruby, INI, Io,
        Jack, Jade, Java, JavaScript, JSON, JSONiq, JSP, JSX,
        Julia, LaTeX, Lean, LESS, Liquid, Lisp, LiveScript, LogiQL,
        LSL, Lua, LuaPage, Lucene, Makefile, Markdown, Mask, MATLAB,
        Maze, MEL, MUSHCode, MySQL, Nix, ObjectiveC, OCaml, Pascal,
        Perl, pgSQL, PHP, Powershell, Praat, Prolog, Properties, Protobuf,
        Python, R, RDoc, RHTML, Ruby, Rust, SASS, SCAD,
        Scala, Scheme, SCSS, SH, SJS, Smarty, snippets, Soy_Template,
        Space, SQL, SQLServer, Stylus, SVG, Swift, Tcl, Tex,
        Text, Textile, Toml, Twig, Typescript, Vala, VBScript, Velocity,
        Verilog, VHDL, XML, XQuery, YAML, Django,};

    /**
     * Checks whether an extension is supported by the editor or not.
     *
     * @param extension Extension to check.
     * @return
     */
    public static boolean isSupported(String extension) {
        for (ModeData md : SUPPORTED_MODES) {
            if (md.supportsFile(extension)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Gets the mode to set by its name
     *
     * @param name Mode name.
     * @return
     */
    public static ModeData getModeByName(String name) {
        for (ModeData mode : SUPPORTED_MODES) {
            if (mode.getName().equals(name)) {
                return mode;
            }
        }
        return null;
    }

    /**
     * Gets the name of the mode
     *
     * @param alias alias of the mode like <code>"ace/mode/java"</code>
     * @return
     */
    public static ModeData getModeByAlias(String alias) {
        for (ModeData md : SUPPORTED_MODES) {
            if (md.getAlias().equals(alias)) {
                return md;
            }
        }
        return null;
    }

    /**
     * Gets the language mode of a file. If file is not supported mode for
     * <code>*.txt</code> files is returned.
     *
     * @param path Name or location of the file with extension.
     * @return
     */
    public static ModeData getModeFromFile(String path) {
        for (ModeData md : SUPPORTED_MODES) {
            if (md.supportsFile(path)) {
                return md;
            }
        }
        return null;
    }
}
