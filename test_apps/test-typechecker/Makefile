ALL_APP = $(wildcard *.app)
TESTS = $(ALL_APP:.app=)

LN_S = ln -s

check: $(TESTS)
	@pass=0; fail=0; \
	for i in $(TESTS); do \
		if $(TESTENVIRONMENT) ./$$i; then\
		  echo "PASS: $$i"; \
		  pass=$$(($pass + 1)); \
	  else \
			echo "FAIL: $$i"; \
	    fail=$$(($fail + 1)); \
		fi; \
	done; \
	echo ;\
	echo "Total:"; \
	echo "PASS: $$pass"; \
	echo "FAIL: $$fail";

$(TESTS): run-test.sh
	@$(LN_S) $< $@
	@chmod +x $@

clean:
	-@rm -f $(TESTS)
