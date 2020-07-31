#test = open("tttt.in", "w")
#test.write("COW\nXXO\nABC")
#test.close()

inFile = open("tttt.in", "r")
outFile = open("tttt.out", "w")

grid = []
grid.append(inFile.readline())
grid.append(inFile.readline())
grid.append(inFile.readline())

sets = []
for i in range(3):
    sets.append(set([grid[i][0], grid[i][1], grid[i][2]]))
    sets.append(set([grid[0][i], grid[1][i], grid[2][i]]))

sets.append(set([grid[0][0], grid[1][1],grid[2][2]]))
sets.append(set([grid[0][2], grid[1][1],grid[2][0]]))

ones = []
twos = []
print(sets)
for thing in sets:
    if len(thing) == 1 and thing not in ones:
        ones.append(thing)
    if len(thing) == 2 and thing not in twos:
        twos.append(thing)

outFile.write(str(len(ones)) + "\n" + str(len(twos)))
             
inFile.close()
outFile.close()
