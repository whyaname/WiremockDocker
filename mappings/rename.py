import os
import re
import glob

filesnames = glob.iglob("/Users/anavajain/Downloads/WiremockDocker/mappings/*.json")
pattern = re.compile(r'(?<=first\\\":\\\").*?(?=\\\")')
pattern2 = re.compile(r'(?<=last\\\":\\\").*?(?=\\\")')
for f in filesnames:
    t = open(f)
    z = t.read()
    a = re.search(pattern, z).group(0)
    b = re.search(pattern2, z).group(0)
    new_file_name = a+b+".json"
    os.rename(f, new_file_name)
