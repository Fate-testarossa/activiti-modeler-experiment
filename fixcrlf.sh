#!/bin/sh

IFS=$'\n'

for file in $(find ./ -type f \( -name '*.js' -o -name '*.json' -o -name '*.java' -o -name '*.xml' -o -name '*.properties' \) ); do
  perl -i -p -e 's/\r\n/\n/g' "$file"
  perl -i -p -e 's/\r/\n/g' "$file"
  perl -i -p -e 's/\t/    /g' "$file"
  perl -i -p -e 's/^\s+$/\n/g' "$file"
  perl -i -p -e 's/\s+$/\n/g' "$file"
done

