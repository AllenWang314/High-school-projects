#test = open("revegetate.in", "w")
#test.write("5 6\n4 1\n4 2\n4 3\n2 5\n1 2\n1 5")
#test.close()

inFile = open("revegetate.in", "r")
outFile = open("revegetate.out", "w")

line = inFile.readline().split()
p, m = int(line[0]), int(line[1])
print(p)
print(m)

ans = ""
avail = []
adj = []
for i in range(p):
    adj.append([])
    avail.append([1,2,3,4])

for i in range(m):
    line = inFile.readline().split()
    first, second = int(line[0])-1, int(line[1])-1
    (adj[first]).append(second)
    (adj[second]).append(first)

for i in range(p):
    print(ans)
    print(adj)
    print(avail)
    ans += str(min(avail[i]))
    for j in adj[i]:
        if (min(avail[i]) in avail[j]):
            avail[j].remove(min(avail[i]))
        adj[j].remove(i)

#print(ans)

outFile.write(str(ans))
inFile.close()
outFile.close()
