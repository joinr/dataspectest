(ns dataspectest.core
  (:require
   [spec-tools.data-spec :as ds]
   [clojure.spec.gen.alpha :as gen]
   [clojure.spec.alpha :as s]))

;; My structure defined in a schema-like syntax using spec-tools.data-spec
(def input-correlations-concise
  (ds/spec
   ::input-correlations-concise
   {:input keyword?
    :score int?
    :average float?
    :correlations [{:biomarker keyword?
                    :regression-results {:slope float?
                                         :rsq float?
                                         :datapoints int?}}]}))

(def tst {:input :blah
          :score 2
          :average 2.0
          :correlations [{:biomarker :a
                          :regression-results {:slope 2.0
                                               :rsq 1.0
                                               :datapoints 2}}]})
;;
(s/valid? input-correlations-concise tst) ;;true
(s/valid? input-correlations-concise (assoc tst :input "blah")) ;;false

;;register as a spec (seems necessary for some reason):
(s/def ::input-correlations-concise input-correlations-concise)
(s/valid? ::input-correlations-concise tst) ;;true
(s/valid? ::input-correlations-concise (assoc tst :input "blah")) ;;false


; Unfortunately, this throws an exception and I'm not sure why
(gen/generate (s/gen ::input-correlations-concise))
;; {:input :-/b!O+,
;;  :score 21970,
;;  :average 0.23876953125,
;;  :correlations
;;  [{:biomarker :E.sZ.d/xB,
;;    :regression-results {:slope -4.787053108215332, :rsq -64.0, :datapoints 0}}
;;   {:biomarker :ng61*w7*/.?+N,
;;    :regression-results
;;    {:slope 0.125, :rsq -65.5866928100586, :datapoints 419354}}
;;   {:biomarker :M!-/Zl,
;;    :regression-results
;;    {:slope 5.265625, :rsq -0.048583984375, :datapoints -202051}}
;;   {:biomarker :_Pz6.*5/vp2z,
;;    :regression-results
;;    {:slope -0.009850502014160156,
;;     :rsq -0.0103607177734375,
;;     :datapoints -1835663}}
;;   {:biomarker :idsF6Q-0/n42?T,
;;    :regression-results
;;    {:slope 0.06709038000553846, :rsq -0.0, :datapoints 132351818}}
;;   {:biomarker :+-sM2_-!/j*U6I5,
;;    :regression-results
;;    {:slope 0.8460707925260067, :rsq 0.5, :datapoints 71083}}
;;   {:biomarker :!7/Nwz__*H,
;;    :regression-results
;;    {:slope 0.3505859375, :rsq -5.107421875, :datapoints -2294}}
;;   {:biomarker :cP/_S,
;;    :regression-results
;;    {:slope 224.0, :rsq -0.1239166259765625, :datapoints -73}}
;;   {:biomarker :q?13b/C+.Cs,
;;    :regression-results
;;    {:slope -0.08203125, :rsq 0.02791009098291397, :datapoints -5266}}
;;   {:biomarker :Z/wN?!:8!,
;;    :regression-results {:slope 21.9609375, :rsq 1.84375, :datapoints 74}}
;;   {:biomarker :m!?7U0/m,
;;    :regression-results
;;    {:slope -0.01373291015625, :rsq 0.4453125, :datapoints -11753455}}
;;   {:biomarker :J76B.F_/.?0,
;;    :regression-results {:slope 0.0078125, :rsq -482.375, :datapoints -3604}}
;;   {:biomarker :S/-X6r+H?,
;;    :regression-results
;;    {:slope 122.46588706970215, :rsq -0.006777137663448229, :datapoints 4168}}
;;   {:biomarker :I?.4/L.*kV,
;;    :regression-results
;;    {:slope 0.044921875, :rsq -0.2921462655067444, :datapoints 1293737}}
;;   {:biomarker :u/dOO,
;;    :regression-results
;;    {:slope 0.1287114918231964,
;;     :rsq 0.9141254425048828,
;;     :datapoints 288739526}}]}
