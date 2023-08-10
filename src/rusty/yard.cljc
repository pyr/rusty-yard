(ns rusty.yard
  "A codename generator, see `codename`"
  (:require [clojure.string :as str]))

(def ^:private ^:no-doc colors
  "A few color for the triple part version of generated names"
  ["white","black","yellow","red","blue","brown","green",
   "purple","orange","silver","scarlet","rainbow","indigo",
   "ivory","navy","olive","teal","pink","magenta","maroon",
   "sienna","gold","golden"])

(def ^:private ^:no-doc adjectives
  "Adjectives make most of the corpus"
  ["abandoned","aberrant","accidentally","aggressive","aimless",
   "alien","angry","appropriate","barbaric","beacon","big","bitter",
   "bleeding","brave","brutal","cheerful","dancing","dangerous",
   "dead","deserted","digital","dirty","disappointed","discarded",
   "dreaded","eastern","eastern","elastic","empty","endless",
   "essential","eternal","everyday","fierce","flaming","flying",
   "forgotten","forsaken","freaky","frozen","full","furious","ghastly",
   "global","gloomy","grim","gruesome","gutsy","helpless","hidden",
   "hideous","homeless","hungry","insane","intense","intensive",
   "itchy","liquid","lone","lost","meaningful","modern",
   "monday's","morbid","moving","needless","nervous","new","next",
   "ninth","nocturnal","northernmost","official","old","permanent",
   "persistent","pointless","pure","quality","random","rare","raw",
   "reborn","remote","restless","rich","risky","rocky","rough",
   "running","rusty","sad","saturday's","screaming","serious",
   "severe","silly","skilled","sleepy","sliding","small","solid",
   "steamy", "stony","stormy","straw","strawberry","streaming",
   "strong","subtle",
   "supersonic","surreal","tainted","temporary","third","tidy",
   "timely",
   "unique","vital","western","wild","wooden","worthy","bitter",
   "boiling",
   "brave","cloudy","cold","confidential","dreadful","dusty","eager",
   "early","grotesque ","harsh","heavy","hollow","hot","husky","icy",
   "late","lonesome","long","lucky","massive","maximum","minimum",
   "mysterious","outstanding","rapid","rebel","scattered","shiny",
   "solid","square","steady","steep","sticky","stormy","strong",
   "sunday's","swift","tasty"])

(def ^:private ^:no-doc default-suffixes
  "By default suffixes are nouns"
  ["alarm","albatross","anaconda","antique","artificial","autopsy",
   "autumn","avenue","backpack","balcony","barbershop","boomerang",
   "bulldozer","butter","canal","cloud","clown","coffin","comic",
   "compass","cosmic","crayon","creek","crossbow","dagger","dinosaur",
   "dog","donut","door","doorstop","electrical","electron","eyelid",
   "firecracker","fish","flag","flannel","flea","frostbite","gravel",
   "haystack","helium","kangaroo","lantern","leather","limousine",
   "lobster","locomotive","logbook","longitude","metaphor",
   "microphone",
   "monkey","moose","morning","mountain","mustard","neutron",
   "nitrogen",
   "notorious","obscure","ostrich","oyster","parachute","peasant",
   "pineapple","plastic","postal","pottery","proton","puppet",
   "railroad",
   "rhinestone","roadrunner","rubber","scarecrow","scoreboard",
   "scorpion",
   "shower","skunk","sound","street","subdivision","summer","sunshine",
   "tea","temple","test","tire","tombstone","toothbrush","torpedo",
   "toupee",
   "trendy","trombone","tuba","tuna","tungsten","vegetable","venom",
   "vulture","waffle","warehouse","waterbird","weather","weeknight",
   "windshield","winter","wrench","xylophone","alpha","arm","beam",
   "beta",
   "bird","breeze","burst","cat","cobra","crystal","drill","eagle",
   "emerald","epsilon","finger","fist","foot","fox","galaxy","gamma",
   "hammer","heart","hook","hurricane","iron","jazz","jupiter","knife",
   "lama","laser","lion","mars","mercury","moon","moose","neptune",
   "omega","panther","planet","pluto","plutonium","poseidon","python",
   "ray","sapphire","scissors","screwdriver","serpent","sledgehammer",
   "smoke","snake","space","spider","star","steel","storm","sun",
   "swallow",
   "tiger","uranium","venus","viper","wrench","yard","zeus"])

(def ^:private ^:no-doc default-corpus
  "The default corpus has both colors and adjectives"
  (vec (concat colors adjectives)))

(def ^:private ^:no-doc default-separator
  "The default separator for code names"
  " ")

(defn codename
  "Generate a name composed of two or three parts. This can be used
   to generate release names for the uninspired. The 0-arity uses an
   internal corpus and a space as separator for the elements. The
   following arguments can be provided as keyword arguments or using
   a map as the first argument:

     - `corpus`:    The list of names. Either one or two elements from this
                    corpus will be picked for every generated name. Prefer
                    adjectives or qualifying words here.
     - `suffixes`:  A list of suffixes, a single one picked for every generated
                    name. Prefer nouns here.
     - `separator`: A string to use to join elements together, defaults to a
                    space.
     - `join?`:     Whether to join using the separator, defaults to true.
                    Otherwise yields a vector.

   "
  ([& {:keys [corpus suffixes separator join?]
       :or   {corpus    default-corpus
              suffixes  default-suffixes
              separator default-separator
              join?     true}}]
   (let [name1  (rand-nth corpus)
         name2  (rand-nth corpus)
         suffix (rand-nth suffixes)
         i      (rand-int 100)]
     (cond->> (cond
                (<= i 15)   [name1 name2 suffix]
                (< 15 i 31) [suffix name1]
                :else       [name1 suffix])
       join?
       (str/join separator)))))

(comment

  ;; Quick examples
  (codename)
  (codename :separator "-")
  (codename {:join? false}))
