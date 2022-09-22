find . -type f | xargs ls -lS | head -1 | awk '{print "LONGEST ONE LINER\ncharacters: " $5 "\nfile: "  $NF}'

#LONGEST ONE LINER
#characters: 859630
#file: ./.git/objects/1f/48c5cd8a2ea7bf21c115d5686caa06f82a2c5c
#xargs: ls: terminated by signal 13
