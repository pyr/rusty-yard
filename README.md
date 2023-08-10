rusty yard: when you feel uninspired
====================================

Somewhat randomly generates names that you can remember easily.

## Usage

### <a name="rusty.yard/codename">`codename`</a><a name="rusty.yard/codename"></a>
``` clojure

(codename
 &
 {:keys [corpus suffixes separator join?],
  :or {corpus default-corpus, suffixes default-suffixes, separator default-separator, join? true}})
```

Generate a name composed of two or three parts. This can be used
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


<p><sub><a href="https://github.com/pyr/rusty-yard/blob/main/src/rusty/yard.cljc#L86-L118">Source</a></sub></p>
