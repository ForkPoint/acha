(defproject acha "0.3.0"
  :description "Enterprise Git Achievements Provider. Web scale. In the cloud"
  :url "http://forkpoint.com"

  :global-vars  {*warn-on-reflection* true}
  :source-paths ["src-clj"]
  :main         acha.server
  :aot          [acha.server]
  :uberjar-name "acha-uber.jar"
  :uberjar-exclusions [#".*\.piko"
                       #"public/out/.*"
                       #"public/index_dev\.html"
                       #"public/react-.*"]

  :dependencies [
    [org.clojure/clojure "1.10.1"]
    [org.clojure/tools.logging "1.1.0"]
    [org.clojure/tools.cli "1.0.194"]
    [ch.qos.logback/logback-classic "1.2.3"]

    [http-kit "2.5.1"]
    [ring/ring-core "1.5.1" :exclusions [commons-codec org.clojure/tools.reader]]
    [ring/ring-devel "1.8.2"]
    [compojure "1.6.2"]
    [com.cognitect/transit-clj "1.0.324" :exclusions [org.msgpack/msgpack org.clojure/test.check]]

    [clj-jgit "1.0.1" :exclusions [org.clojure/core.memoize]]
    

    [org.clojure/java.jdbc "0.7.11"]
    [org.xerial/sqlite-jdbc "3.34.0"]
    [com.mchange/c3p0 "0.9.5.5"]
    
    [org.clojure/clojurescript "1.10.764"]

    [rum "0.12.3" :exclusions [cljsjs/react]]
    [cljsjs/react-with-addons "15.6.1-0"]

    [org.clojure/core.async "1.3.610"]
    [com.cognitect/transit-cljs "0.8.264"]
    [datascript "1.0.3"]
    [sablono "0.8.6" :exclusions [cljsjs/react]]
  ]

  :plugins [
    [lein-ring "0.12.5"]
    [lein-cljsbuild "1.1.8"]
  ]
  :clean-targets ^{:protect false} [
    "target"
    "resources/public/out"
    "resources/public/acha.min.js"
    "resources/public/acha.js"
  ]
  :hooks [leiningen.cljsbuild]
  :cljsbuild { 
    :builds [
      { :id "prod"
        :source-paths ["src-cljs"]
        :jar true
        :compiler {
          :preamble      ["public/md5.js"]
          :output-to     "resources/public/acha.min.js"
          :optimizations :advanced
          :pretty-print  false
        }}
  ]}

 
  :profiles {
    :dev {
      :cljsbuild {
        :builds [
          { :id "dev"
            :source-paths ["src-cljs"]
            :compiler {
              :output-to     "resources/public/acha.js"
              :output-dir    "resources/public/out"
              :optimizations :none
              :source-map    true
            }}
      ]}
    }
  }
)
