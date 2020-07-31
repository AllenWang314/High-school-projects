fin = open ('teleport.in','r')
fout = open ('teleport.out','w')

lineList = fin.readline()
lineList = lineList.split()
noTele = abs(int(lineList[0]) - int(lineList[1]))
yesTele1 = abs(int(lineList[0]) - int(lineList[2])) + abs(int(lineList[3]) - int(lineList[1]))
yesTele2 = abs(int(lineList[0]) - int(lineList[3])) + abs(int(lineList[2]) - int(lineList[1]))
dist = min(noTele,yesTele1)
dist = min(dist,yesTele2)

print(dist)
fout.write(str(dist))

fout.close()
