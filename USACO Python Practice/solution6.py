#test = open("billboard.in", "w")
#test.write("2 1 7 4\n5 -1 10 3")
#test.close()

inFile = open("billboard.in", "r")
outFile = open("billboard.out", "w")

lawnMower = inFile.readline().split()
tasty = inFile.readline().split()

lawnMower[0] = int(lawnMower[0])
lawnMower[1] = int(lawnMower[1])
lawnMower[2] = int(lawnMower[2])
lawnMower[3] = int(lawnMower[3])

tasty[0] = int(tasty[0])
tasty[1] = int(tasty[1])
tasty[2] = int(tasty[2])
tasty[3] = int(tasty[3])

tasty[0] = max(lawnMower[0],tasty[0])
tasty[1] = max(lawnMower[1],tasty[1])
tasty[2] = min(lawnMower[2],tasty[2])
tasty[3] = min(lawnMower[3],tasty[3])

if tasty[0] == lawnMower[0] and tasty[2] == lawnMower[2] and tasty[1] == lawnMower[1]:
    areaNeeded = (lawnMower[3] - tasty[3]) * (tasty[2]-tasty[0])
elif tasty[0] == lawnMower[0] and tasty[2] == lawnMower[2] and tasty[3] == lawnMower[3]:
    areaNeeded = (lawnMower[1] - tasty[1]) * (tasty[2]-tasty[0])
elif tasty[1] == lawnMower[1] and tasty[3] == lawnMower[3] and tasty[2] == lawnMower[2]:
    areaNeeded = (lawnMower[0] - tasty[0]) * (tasty[3]-tasty[1])
elif tasty[1] == lawnMower[1] and tasty[3] == lawnMower[3] and tasty[0] == lawnMower[0]:
    areaNeeded = (lawnMower[2] - tasty[2]) * (tasty[3]-tasty[1])
else:
    areaNeeded = (lawnMower[2]-lawnMower[0])*(lawnMower[3]-lawnMower[1])
    
outFile.write(str(abs(areaNeeded)))
#print(areaNeeded)
outFile.close()
inFile.close()
