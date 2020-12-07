
DUCKFILES= $(notdir $(wildcard duck/*.duck))
PAGES= $(addprefix pages/, $(DUCKFILES:.duck=.page))
OUTDIR= pages
RECIPE_PAGES_DIR= ../meta-citadel/recipes-citadel/citadel-documentation/files/pages

.PHONY: directories clean

all: directories $(PAGES)

install: directories $(PAGES)
	rm -f ${RECIPE_PAGES_DIR}/*.page
	cp $(PAGES) ${RECIPE_PAGES_DIR}


clean:
	rm $(PAGES)

directories: ${OUTDIR}

${OUTDIR}:
	mkdir -p ${OUTDIR}

pages/%.page: duck/%.duck
	ducktype -o $@ $<
