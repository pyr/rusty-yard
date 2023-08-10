.PHONY: *
.DEFAULT_GOAL:=help

# Internal
CLJ=clojure -J-Dclojure.main.report=stderr
CLEANFILES=target .cpcache node_modules package.json out yarn.lock
SOURCE_PATHS='["src"]'
RM=rm -f

##@ Testing

test: ## Runs Clojure unit tests (lein test)
	$(CLJ) -M:test -m kaocha.runner clj

test-cljs: ## Runs Clojurescript unit tests
	[ -d node_modules ] || npm instal ws
	$(CLJ) -M:test -m kaocha.runner cljs

test-all: ## Runs both test suites
	$(CLJ) -M:test -m kaocha.runner

repl: ## Launch test repl (lein repl)
	$(CLJ) -A:test

##@ Dependencies

deps: ## Show deps tree (lein deps :tree)
	$(CLJ) -Stree

check: ## Compile all namespaces to check for issues (lein compile :all)
	$(CLJ) -X:check check :paths $(SOURCE_PATHS)

setup: ## Ensure everything is working
	@$(CLJ) -M:test -e '(println "everything is ready")'


##@ Misc.

lint: ## runs linting on all modules (clj-kondo)
	clj-kondo --lint src test

format: ## Format according to linter rules (lein cljfmt check)
	$(CLJ) -T:cljfmt check :paths $(SOURCE_PATHS)

format-fix: ## Fix formatting errors found (lein cljfmt fix)
	$(CLJ) -T:cljfmt fix :paths $(SOURCE_PATHS)

outdated: ## run antq (aka 'ancient') task on all modules (lein ancient)
	$(CLJ) -M:test:outdated

doc: ## generate documentation from code
	$(CLJ) -M:quickdoc

clean: ## Clean module target dirs (lein clean)
	$(RM) -r $(CLEANFILES)

##@ Helpers

help:  ## Display this help
	@awk 'BEGIN {FS = ":.*##"; printf "\nUsage:\n  make \033[36m\033[0m\n"} /^[a-zA-Z_-]+:.*?##/ { printf "  \033[36m%-15s\033[0m %s\n", $$1, $$2 } /^##@/ { printf "\n\033[1m%s\033[0m\n", substr($$0, 5) } ' $(MAKEFILE_LIST)
