(ns elastics.cluster
  (:require [elastics.helper :as helper]))


(defn health
  "Gets a very simple status on the health of a cluster"
  ([]
   (health nil))

  ([index]
   {:url    (helper/file "_cluster/health" index)
    :method :get}))


(defn state
  "Gets comprehensive state information of cluster"
  ([]
   (state nil nil))

  ([index metrics]
   {:url    (helper/file "_cluster/state" metrics index)
    :method :get}))


(defn stats
  "Retrieves statistics from a cluster wide perspective."
  []
  {:url    (helper/file "_cluster/stats")
   :method :get})


(defn pending-tasks
  "Retrieves statistics from a cluster wide perspective."
  []
  {:url    (helper/file "_cluster/pending_tasks")
   :method :get})


(defn reroute
  "Executes a reroute allocation command including specific commands."
  [body]
  {:url    (helper/file "_cluster/reroute")
   :method :post
   :body   body})


(defn update-settings
  "Updates cluster wide specific settings."
  [body]
  {:url    (helper/file "_cluster/settings")
   :method :put
   :body   body})


(defn node-stats
  "Retrieve one or more (or all) of the cluster nodes statistics."
  ([]
   (node-stats nil nil))

  ([stats]
   (node-stats stats nil))

  ([stats nodes]
   {:url    (helper/file "_nodes/stats" nodes stats)
    :method :get}))


(defn node-info
  "Retrieve one or more (or all) of the cluster nodes statistics."
  ([]
   (node-info nil nil))

  ([attributes]
   (node-info attributes nil))

  ([attributes nodes]
   {:url    (helper/file "_nodes" nodes attributes)
    :method :get}))


(defn node-hot-threads
  ""
  ([]
   (node-hot-threads nil))

  ([node]
   {:url    (helper/file "_nodes" node "hot_threads")
    :method :get}))
