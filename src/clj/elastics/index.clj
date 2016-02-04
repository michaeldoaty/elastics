(ns elastics.index
  (:require [elastics.helper :as helper])
  (:refer-clojure :exclude [get flush]))


(defn create
  "Instantiates an index."
  ([index]
   (create nil index))

  ([body index]
   {:url    (helper/file index)
    :method :put
    :body   body}))


(defn delete
  "Deletes an existing index."
  [index]
  {:url    (helper/file index)
   :method :delete})


(defn get
  "Retrieves information about one or more indices."
  [index]
  {:url    (helper/file index)
   :method :get})


(defn exists
  "Checks if index exists or not."
  [index]
  {:url    (helper/file index)
   :method :head})


(defn close
  "Closes an index."
  [index]
  {:url    (helper/file index "_close")
   :method :post})


(defn open
  "Opens an index."
  [index]
  {:url    (helper/file index "_open")
   :method :post})


(defn put-mapping
  "Adds a new type to an existing index,
  or new fields to an existing type."
  ([body index]
   {:url    (helper/file index)
    :method :put
    :body   body})

  ([body index type]
   {:url    (helper/file index "_mapping" type)
    :method :put
    :body   body}))


(defn get-mapping
  "Retrieves mapping definitions for an index or index/type."
  ([index]
   (get-mapping index nil))

  ([index type]
   {:url    (helper/file index "_mapping" type)
    :method :get}))


(defn type-exists
  "Checks if a type/types exists in an index/indicies."
  [index type]
  {:url    (helper/file index type)
   :method :head})


(defn aliases
  "Aliases an index with a name."
  [body]
  {:url    (helper/file "_aliases")
   :method :post})


(defn add-alias
  "Adds a single alias"
  [index name]
  {:url    (helper/file index "_alias" name)
   :method :put})


(defn get-alias
  "Retrieves existing alias"
  [index name]
  {:url    (helper/file index "_alias" name)
   :method :get})


(defn delete-alias
  "Deletes an alias"
  [index name]
  {:url    (helper/file index "_alias" name)
   :method :delete})


(defn update-settings
  "Updates index level settings"
  ([body]
   (update-settings body nil))

  ([body index]
   {:url    (helper/file index "_settings")
    :method :put
    :body   body}))


(defn get-settings
  "Retrieves settings of index/indicies."
  ([index]
   {:url    (helper/file index "_settings")
    :method :get}))


(defn analyze
  "Performs the analysis process on a text
  and return the tokens breakdown of the text."
  ([body]
   (analyze body nil))

  ([body index]
   {:url    (helper/file index "_analyze")
    :method :get
    :body   body}))


(defn put-template
  "Adds a template"
  [body template]
  {:url    (helper/file "_template" template)
   :method :put
   :body   body})


(defn delete-template
  "Deletes a template."
  [template]
  {:url    (helper/file "_template" template)
   :method :delete})


(defn get-template
  "Retrieves a template."
  [template]
  {:url    (helper/file "_template" template)
   :method :get})


(defn template-exists
  "Checks the existence of a template."
  [template]
  {:url    (helper/file "_template" template)
   :method :head})


(defn put-warmer
  "Adds a warmer to a specific index."
  ([body warmer]
   (put-warmer body nil nil warmer))

  ([body index warmer]
   (put-warmer body index nil warmer))

  ([body index type warmer]
   {:url    (helper/file index type "_warmer" warmer)
    :method :put
    :body   body}))


(defn delete-warmer
  "Deletes warmer"
  [index warmer]
  {:url    (helper/file index "_warmer" warmer)
   :method :delete})


(defn get-warmer
  "Retrieves warmer"
  [index warmer]
  {:url    (helper/file index "_warmer" warmer)
   :method :get})


(defn stats
  "Provides statistics on different operations happening on an index."
  ([]
   (stats nil))

  ([index]
   {:url    (helper/file index "_stats")
    :method :get}))


(defn segments
  "Gets low level segments information that a Lucene index
  (shard-level) is built with."
  ([]
   (segments nil))

  ([index]
   {:url    (helper/file index "_segments")
    :method :get}))


(defn recovery
  "Provides insight into on-going index shard recoveries."
  ([]
   (recovery nil))

  ([index]
   {:url    (helper/file index "_recovery")
    :method :get}))


(defn shard-stores
  "Provides store information for shard copies of indicies."
  ([]
   (shard-stores nil))

  ([index]
   {:url    (helper/file index "_shard_stores")
    :method :get}))


(defn clear-cache
  "Clears all caches or specified cached associated with one or more indicies."
  ([]
   (clear-cache nil))

  ([index]
   {:url    (helper/file index "_cache/clear")
    :method :post}))


(defn flush
  "Flushes one or more indicies."
  ([]
   (flush nil))

  ([index]
   {:url    (helper/file index "_flush")
    :method :post}))


(defn synced-flush
  "Initiates a synced flush manually."
  ([]
   (synced-flush nil))

  ([index]
   {:url    (helper/file index "_flush/synced")
    :method :post}))


(defn refresh
  "Refreshes one or more indicies."
  ([]
   (refresh nil))

  ([index]
   {:url    (helper/file index "_refresh")
    :method :post}))


(defn force-merge
  "Merges one or more indicies."
  ([]
   (force-merge nil))

  ([index]
   {:url    (helper/file index "_forcemerge")
    :method :post}))


(defn upgrade
  "Upgrades one or more indicies to the last Lucene format."
  ([]
   (upgrade nil))

  ([index]
   {:url    (helper/file index "_upgrade")
    :method :post}))
