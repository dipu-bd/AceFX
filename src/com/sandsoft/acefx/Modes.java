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
package com.sandsoft.acefx;

import java.util.Arrays;

/**
 *
 * @author Sudipto Chandra
 */
public final class Modes {

    public static final String ABAP = "ace/mode/abap";
    public static final String ABC = "ace/mode/abc";
    public static final String ActionScript = "ace/mode/as";
    public static final String ADA = "ace/mode/ada";
    //public static final String ADA = "ace/mode/adb";
    public static final String Apache_Conf = "ace/mode/^htaccess";
    //public static final String Apache_Conf = "ace/mode/^htgroups";
    //public static final String Apache_Conf = "ace/mode/^htpasswd";
    //public static final String Apache_Conf = "ace/mode/^conf";
    //public static final String Apache_Conf = "ace/mode/htaccess";
    //public static final String Apache_Conf = "ace/mode/htgroups";
    //public static final String Apache_Conf = "ace/mode/htpasswd";
    public static final String AsciiDoc = "ace/mode/asciidoc";
    //public static final String AsciiDoc = "ace/mode/adoc";
    public static final String Assembly_x86 = "ace/mode/asm";
    //public static final String Assembly_x86 = "ace/mode/a";
    public static final String AutoHotKey = "ace/mode/ahk";
    public static final String BatchFile = "ace/mode/bat";
    //public static final String BatchFile = "ace/mode/cmd";
    public static final String C_Cpp = "ace/mode/cpp";
    //public static final String C_Cpp = "ace/mode/c";
    //public static final String C_Cpp = "ace/mode/cc";
    //public static final String C_Cpp = "ace/mode/cxx";
    //public static final String C_Cpp = "ace/mode/h";
    //public static final String C_Cpp = "ace/mode/hh";
    //public static final String C_Cpp = "ace/mode/hpp";
    //public static final String C_Cpp = "ace/mode/ino";
    public static final String C9Search = "ace/mode/c9search_results";
    public static final String Cirru = "ace/mode/cirru";
    //public static final String Cirru = "ace/mode/cr";
    public static final String Clojure = "ace/mode/clj";
    //public static final String Clojure = "ace/mode/cljs";
    public static final String Cobol = "ace/mode/CBL";
    //public static final String Cobol = "ace/mode/COB";
    public static final String coffee = "ace/mode/coffee";
    //public static final String coffee = "ace/mode/cf";
    //public static final String coffee = "ace/mode/cson";
    //public static final String coffee = "ace/mode/^Cakefile";
    public static final String ColdFusion = "ace/mode/cfm";
    public static final String CSharp = "ace/mode/cs";
    public static final String CSS = "ace/mode/css";
    public static final String Curly = "ace/mode/curly";
    public static final String D = "ace/mode/d";
    //public static final String D = "ace/mode/di";
    public static final String Dart = "ace/mode/dart";
    public static final String Diff = "ace/mode/diff";
    //public static final String Diff = "ace/mode/patch";
    public static final String Dockerfile = "ace/mode/^Dockerfile";
    public static final String Dot = "ace/mode/dot";
    public static final String Dummy = "ace/mode/dummy";
    public static final String DummySyntax = "ace/mode/dummy";
    public static final String Eiffel = "ace/mode/e";
    //public static final String Eiffel = "ace/mode/ge";
    public static final String EJS = "ace/mode/ejs";
    public static final String Elixir = "ace/mode/ex";
    //public static final String Elixir = "ace/mode/exs";
    public static final String Elm = "ace/mode/elm";
    public static final String Erlang = "ace/mode/erl";
    //public static final String Erlang = "ace/mode/hrl";
    public static final String Forth = "ace/mode/frt";
    //public static final String Forth = "ace/mode/fs";
    //public static final String Forth = "ace/mode/ldr";
    public static final String FTL = "ace/mode/ftl";
    public static final String Gcode = "ace/mode/gcode";
    public static final String Gherkin = "ace/mode/feature";
    public static final String Gitignore = "ace/mode/^.gitignore";
    public static final String Glsl = "ace/mode/glsl";
    //public static final String Glsl = "ace/mode/frag";
    //public static final String Glsl = "ace/mode/vert";
    public static final String golang = "ace/mode/go";
    public static final String Groovy = "ace/mode/groovy";
    public static final String HAML = "ace/mode/haml";
    public static final String Handlebars = "ace/mode/hbs";
    //public static final String Handlebars = "ace/mode/handlebars";
    //public static final String Handlebars = "ace/mode/tpl";
    //public static final String Handlebars = "ace/mode/mustache";
    public static final String Haskell = "ace/mode/hs";
    public static final String haXe = "ace/mode/hx";
    public static final String HTML = "ace/mode/html";
    //public static final String HTML = "ace/mode/htm";
    //public static final String HTML = "ace/mode/xhtml";
    public static final String HTML_Ruby = "ace/mode/erb";
    //public static final String HTML_Ruby = "ace/mode/rhtml";
    //public static final String HTML_Ruby = "ace/mode/html.erb";
    public static final String HTML_Elixir = "ace/mode/eex";
    //public static final String HTML_Elixir = "ace/mode/html.eex";
    public static final String INI = "ace/mode/ini";
    //public static final String INI = "ace/mode/conf";
    //public static final String INI = "ace/mode/cfg";
    //public static final String INI = "ace/mode/prefs";
    public static final String Io = "ace/mode/io";
    public static final String Jack = "ace/mode/jack";
    public static final String Jade = "ace/mode/jade";
    public static final String Java = "ace/mode/java";
    public static final String JavaScript = "ace/mode/js";
    //public static final String JavaScript = "ace/mode/jsm";
    //public static final String JavaScript = "ace/mode/jsx";
    public static final String JSON = "ace/mode/json";
    public static final String JSONiq = "ace/mode/jq";
    public static final String JSP = "ace/mode/jsp";
    public static final String JSX = "ace/mode/jsx";
    public static final String Julia = "ace/mode/jl";
    public static final String LaTeX = "ace/mode/tex";
    //public static final String LaTeX = "ace/mode/latex";
    //public static final String LaTeX = "ace/mode/ltx";
    //public static final String LaTeX = "ace/mode/bib";
    public static final String Lean = "ace/mode/lean";
    //public static final String Lean = "ace/mode/hlean";
    public static final String LESS = "ace/mode/less";
    public static final String Liquid = "ace/mode/liquid";
    public static final String Lisp = "ace/mode/lisp";
    public static final String LiveScript = "ace/mode/ls";
    public static final String LogiQL = "ace/mode/logic";
    //public static final String LogiQL = "ace/mode/lql";
    public static final String LSL = "ace/mode/lsl";
    public static final String Lua = "ace/mode/lua";
    public static final String LuaPage = "ace/mode/lp";
    public static final String Lucene = "ace/mode/lucene";
    public static final String Makefile = "ace/mode/^Makefile";
    //public static final String Makefile = "ace/mode/^GNUmakefile";
    //public static final String Makefile = "ace/mode/^makefile";
    //public static final String Makefile = "ace/mode/^OCamlMakefile";
    //public static final String Makefile = "ace/mode/make";
    public static final String Markdown = "ace/mode/md";
    //public static final String Markdown = "ace/mode/markdown";
    public static final String Mask = "ace/mode/mask";
    public static final String MATLAB = "ace/mode/matlab";
    public static final String Maze = "ace/mode/mz";
    public static final String MEL = "ace/mode/mel";
    public static final String MUSHCode = "ace/mode/mc";
    //public static final String MUSHCode = "ace/mode/mush";
    public static final String MySQL = "ace/mode/mysql";
    public static final String Nix = "ace/mode/nix";
    public static final String ObjectiveC = "ace/mode/m";
    //public static final String ObjectiveC = "ace/mode/mm";
    public static final String OCaml = "ace/mode/ml";
    //public static final String OCaml = "ace/mode/mli";
    public static final String Pascal = "ace/mode/pas";
    //public static final String Pascal = "ace/mode/p";
    public static final String Perl = "ace/mode/pl";
    //public static final String Perl = "ace/mode/pm";
    public static final String pgSQL = "ace/mode/pgsql";
    public static final String PHP = "ace/mode/php";
    //public static final String PHP = "ace/mode/phtml";
    //public static final String PHP = "ace/mode/shtml";
    //public static final String PHP = "ace/mode/php3";
    //public static final String PHP = "ace/mode/php4";
    //public static final String PHP = "ace/mode/php5";
    //public static final String PHP = "ace/mode/phps";
    //public static final String PHP = "ace/mode/phpt";
    //public static final String PHP = "ace/mode/aw";
    //public static final String PHP = "ace/mode/ctp";
    public static final String Powershell = "ace/mode/ps1";
    public static final String Praat = "ace/mode/praat";
    //public static final String Praat = "ace/mode/praatscript";
    //public static final String Praat = "ace/mode/psc";
    //public static final String Praat = "ace/mode/proc";
    public static final String Prolog = "ace/mode/plg";
    //public static final String Prolog = "ace/mode/prolog";
    public static final String Properties = "ace/mode/properties";
    public static final String Protobuf = "ace/mode/proto";
    public static final String Python = "ace/mode/py";
    public static final String R = "ace/mode/r";
    public static final String RDoc = "ace/mode/Rd";
    public static final String RHTML = "ace/mode/Rhtml";
    public static final String Ruby = "ace/mode/rb";
    //public static final String Ruby = "ace/mode/ru";
    //public static final String Ruby = "ace/mode/gemspec";
    //public static final String Ruby = "ace/mode/rake";
    //public static final String Ruby = "ace/mode/^Guardfile";
    //public static final String Ruby = "ace/mode/^Rakefile";
    //public static final String Ruby = "ace/mode/^Gemfile";
    public static final String Rust = "ace/mode/rs";
    public static final String SASS = "ace/mode/sass";
    public static final String SCAD = "ace/mode/scad";
    public static final String Scala = "ace/mode/scala";
    public static final String Scheme = "ace/mode/scm";
    //public static final String Scheme = "ace/mode/sm";
    //public static final String Scheme = "ace/mode/rkt";
    //public static final String Scheme = "ace/mode/oak";
    //public static final String Scheme = "ace/mode/scheme";
    public static final String SCSS = "ace/mode/scss";
    public static final String SH = "ace/mode/sh";
    //public static final String SH = "ace/mode/bash";
    //public static final String SH = "ace/mode/^.bashrc";
    public static final String SJS = "ace/mode/sjs";
    public static final String Smarty = "ace/mode/smarty";
    //public static final String Smarty = "ace/mode/tpl";
    public static final String snippets = "ace/mode/snippets";
    public static final String Soy_Template = "ace/mode/soy";
    public static final String Space = "ace/mode/space";
    public static final String SQL = "ace/mode/sql";
    public static final String SQLServer = "ace/mode/sqlserver";
    public static final String Stylus = "ace/mode/styl";
    //public static final String Stylus = "ace/mode/stylus";
    public static final String SVG = "ace/mode/svg";
    public static final String Swift = "ace/mode/swift";
    public static final String Tcl = "ace/mode/tcl";
    public static final String Tex = "ace/mode/tex";
    public static final String Text = "ace/mode/txt";
    public static final String Textile = "ace/mode/textile";
    public static final String Toml = "ace/mode/toml";
    public static final String Twig = "ace/mode/twig";
    //public static final String Twig = "ace/mode/swig";
    public static final String Typescript = "ace/mode/ts";
    //public static final String Typescript = "ace/mode/typescript";
    //public static final String Typescript = "ace/mode/str";
    public static final String Vala = "ace/mode/vala";
    public static final String VBScript = "ace/mode/vbs";
    //public static final String VBScript = "ace/mode/vb";
    public static final String Velocity = "ace/mode/vm";
    public static final String Verilog = "ace/mode/v";
    //public static final String Verilog = "ace/mode/vh";
    //public static final String Verilog = "ace/mode/sv";
    //public static final String Verilog = "ace/mode/svh";
    public static final String VHDL = "ace/mode/vhd";
    //public static final String VHDL = "ace/mode/vhdl";
    public static final String XML = "ace/mode/xml";
    //public static final String XML = "ace/mode/rdf";
    //public static final String XML = "ace/mode/rss";
    //public static final String XML = "ace/mode/wsdl";
    //public static final String XML = "ace/mode/xslt";
    //public static final String XML = "ace/mode/atom";
    //public static final String XML = "ace/mode/mathml";
    //public static final String XML = "ace/mode/mml";
    //public static final String XML = "ace/mode/xul";
    //public static final String XML = "ace/mode/xbl";
    //public static final String XML = "ace/mode/xaml";
    public static final String XQuery = "ace/mode/xq";
    public static final String YAML = "ace/mode/yaml";
    //public static final String YAML = "ace/mode/yml";
    public static final String Django = "ace/mode/html";

    public static final String[] SUPPORTED_MODES = {
        "ABAP", "ABC", "ADA", "ActionScript", "Apache_Conf", "AsciiDoc", "Assembly_x86", "AutoHotKey",
        "BatchFile", "C9Search", "CSS", "CSharp", "C_Cpp", "Cirru", "Clojure", "Cobol",
        "ColdFusion", "Curly", "D", "Dart", "Diff", "Django", "Dockerfile", "Dot",
        "Dummy", "DummySyntax", "EJS", "Eiffel", "Elixir", "Elm", "Erlang", "FTL",
        "Forth", "Gcode", "Gherkin", "Gitignore", "Glsl", "Groovy", "HAML", "HTML",
        "HTML_Elixir", "HTML_Ruby", "Handlebars", "Haskell", "INI", "Io", "JSON", "JSONiq",
        "JSP", "JSX", "Jack", "Jade", "Java", "JavaScript", "Julia", "LESS",
        "LSL", "LaTeX", "Lean", "Liquid", "Lisp", "LiveScript", "LogiQL", "Lua",
        "LuaPage", "Lucene", "MATLAB", "MEL", "MUSHCode", "Makefile", "Markdown", "Mask",
        "Maze", "MySQL", "Nix", "OCaml", "ObjectiveC", "PHP", "Pascal", "Perl",
        "Powershell", "Praat", "Prolog", "Properties", "Protobuf", "Python", "R", "RDoc",
        "RHTML", "Ruby", "Rust", "SASS", "SCAD", "SCSS", "SH", "SJS",
        "SQL", "SQLServer", "SVG", "Scala", "Scheme", "Smarty", "Soy_Template", "Space",
        "Stylus", "Swift", "Tcl", "Tex", "Text", "Textile", "Toml", "Twig",
        "Typescript", "VBScript", "VHDL", "Vala", "Velocity", "Verilog", "XML", "XQuery",
        "YAML", "coffee", "golang", "haXe", "pgSQL", "snippets"
    };

    public static final String[] SUPPORTED_EXTS = {
        "CBL", "COB", "Rd", "Rhtml", "^.bashrc", "^.gitignore", "^Cakefile", "^Dockerfile",
        "^GNUmakefile", "^Gemfile", "^Guardfile", "^Makefile", "^OCamlMakefile", "^Rakefile", "^conf", "^htaccess",
        "^htgroups", "^htpasswd", "^makefile", "a", "abap", "abc", "ada", "adb",
        "adoc", "ahk", "as", "asciidoc", "asm", "atom", "aw", "bash",
        "bat", "bib", "c", "c9search_results", "cc", "cf", "cfg", "cfm",
        "cirru", "clj", "cljs", "cmd", "coffee", "conf", "cpp", "cr",
        "cs", "cson", "css", "ctp", "curly", "cxx", "d", "dart",
        "di", "diff", "dot", "dummy", "dummy", "e", "eex", "ejs",
        "elm", "erb", "erl", "ex", "exs", "feature", "frag", "frt",
        "fs", "ftl", "gcode", "ge", "gemspec", "glsl", "go", "groovy",
        "h", "haml", "handlebars", "hbs", "hh", "hlean", "hpp", "hrl",
        "hs", "htaccess", "htgroups", "htm", "html", "html", "html.eex", "html.erb",
        "htpasswd", "hx", "ini", "ino", "io", "jack", "jade", "java",
        "jl", "jq", "js", "jsm", "json", "jsp", "jsx", "jsx",
        "latex", "ldr", "lean", "less", "liquid", "lisp", "logic", "lp",
        "lql", "ls", "lsl", "ltx", "lua", "lucene", "m", "make",
        "markdown", "mask", "mathml", "matlab", "mc", "md", "mel", "ml",
        "mli", "mm", "mml", "mush", "mustache", "mysql", "mz", "nix",
        "oak", "p", "pas", "patch", "pgsql", "php", "php3", "php4",
        "php5", "phps", "phpt", "phtml", "pl", "plg", "pm", "praat",
        "praatscript", "prefs", "proc", "prolog", "properties", "proto", "ps1", "psc",
        "py", "r", "rake", "rb", "rdf", "rhtml", "rkt", "rs",
        "rss", "ru", "sass", "scad", "scala", "scheme", "scm", "scss",
        "sh", "shtml", "sjs", "sm", "smarty", "snippets", "soy", "space",
        "sql", "sqlserver", "str", "styl", "stylus", "sv", "svg", "svh",
        "swift", "swig", "tcl", "tex", "tex", "textile", "toml", "tpl",
        "tpl", "ts", "twig", "txt", "typescript", "v", "vala", "vb",
        "vbs", "vert", "vh", "vhd", "vhdl", "vm", "wsdl", "xaml",
        "xbl", "xhtml", "xml", "xq", "xslt", "xul", "yaml", "yml"
    };

    /**
     * Checks whether an extension is supported by the editor or not.
     *
     * @param extension Extension to check.
     * @return
     */
    public static boolean isSupported(String extension) {
        if (extension.startsWith(".")) {
            extension = extension.substring(1).toLowerCase();
        }
        return (Arrays.binarySearch(SUPPORTED_EXTS, extension) >= 0);
    }
}
