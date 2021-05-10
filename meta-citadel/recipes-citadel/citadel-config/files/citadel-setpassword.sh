#!/bin/bash

if [ ! -f /storage/citadel-state/passwd ]; then
    /usr/bin/echo -n "citadel:aadg8rGtZzOY6" > /storage/citadel-state/passwd
fi
