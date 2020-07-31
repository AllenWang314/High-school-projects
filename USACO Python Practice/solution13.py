#test = open("backforth.in", "w")
#test.write("1 1 1 1 1 1 1 1 1 2\n5 5 5 5 5 5 5 5 5 5")
#test.close()

inFile = open("backforth.in", "r")
outFile = open("backforth.out", "w")

bb1 = inFile.readline().split()
bb2 = inFile.readline().split()

for i in range(10):
    bb1[i] = int(bb1[i])
    bb2[i] = int(bb2[i])

stuff = set([])

for a in range(10):
    for b in range(11):
        for c in range(10):
            for d in range(11):
                t = 1000
                b1 = bb1.copy()
                b2 = bb2.copy()
                t -= b1[a]
                b2.append(b1[a])
                b1.remove(b1[a])
                t += b2[b]
                b1.append(b2[b])
                b2.remove(b2[b])
                t -= b1[c]
                b2.append(b1[c])
                b1.remove(b1[c])
                t += b2[d]
                b1.append(b2[d])
                b2.remove(b2[d])
                stuff.add(t)

outFile.write(str(len(stuff)))
inFile.close()
outFile.close()
