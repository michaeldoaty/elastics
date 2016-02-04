(ns elastics.core
  (:require [elastics.helper :as helper]
            [clj-http.client :as http]
            [cheshire.core :as json]))

(defn custom
  "Creates a custom request."
  ([method url]
   (custom nil method url))

  ([body method url]
   {:url    url
    :method method
    :body   body}))


(defn params
  "Adds query-params to map."
  [m params]
  (assoc m :query-params params))


(defn extend-url
  "Extends a url by appending a path to the provided map's url."
  [m url]
  (assoc m :url (helper/file (:url m) url)))


(defn merge-http-map
  "Extends http map used during the http call.
  Valid options are clj-http options."
  [m http-map]
  (assoc m :http-map http-map))


(defn build
  "Builds request given the conn and request map."
  [m conn]
  (let [{:keys [http-map body url method query-params]} m
        url (helper/create-url (:url conn) url)]

    {:url      url
     :method   method
     :http-map (merge {:query-params query-params
                       :as           :json
                       :body         (when body (json/generate-string body))}
                      http-map)}))


(defn run
  "Takes a connection and request map, builds the request
  and makes an http call."
  [m conn]
  (let [{:keys [url method http-map]} (build m conn)
        methods   {:get    http/get
                   :head   http/head
                   :post   http/post
                   :put    http/put
                   :delete http/delete}
        http-call (method methods)]
    (http-call url http-map)))

