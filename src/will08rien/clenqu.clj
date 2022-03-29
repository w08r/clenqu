(ns will08rien.clenqu
  (:gen-class))

(import 'org.jline.reader.LineReaderBuilder)
(import 'org.jline.reader.LineReader)
(import 'org.jline.widget.AutosuggestionWidgets)
(import 'org.jline.reader.impl.completer.StringsCompleter)


;; LineReader reader = LineReaderBuilder.builder()
;;                     .terminal(terminal)
;;                     .completer(completer)
;;                     .parser(parser)
;;                     .build();
;; // Create autosuggestion widgets
;; AutosuggestionWidgets autosuggestionWidgets = new AutosuggestionWidgets(reader);
;; // Enable autosuggestions 
;; autosuggestionWidgets.enable();

(defn ^LineReader lr []
  ; see https://clojure.atlassian.net/browse/CLJ-2631
  (let [c (StringsCompleter. ^"[Ljava.lang.String;" (into-array String ["aaa" "bbb" "ccc"]))
        r (-> (LineReaderBuilder/builder)
              (.completer c)
              (.build))
        a (-> (AutosuggestionWidgets. r)
              (.enable))]
  r))
      
(defn greet
  "Callable entry point to the application."
  [data]
  (let [l (lr)]
    (.readLine l)
    (.readLine l)))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (greet {:name (first args)}))
