test = open("factory.in", "w")
test.write("3\n1 2\n3 2")
test.close()

inFile = open("factory.in", "r")
outFile = open("factory.out", "w")

num = int(inFile.readline())
adj = []
canReach = []

for i in range(num):
    adj.append(set([]))
    canReach.append([i])

for i in range(num-1):
    line = inFile.readline().split()
    adj[int(line[0])-1].add(int(line[1])-1)

def flood(i):
    if len(adj[i]) == 0:
        canReach[i].append(i)
    else:
        for j in adj[i]:
            flood(j)
            canReach[i].extend(canReach[j])

for i in range(num):
    flood(i)

def check(i):
    for j in range(num):
        if i not in canReach[j]:
            return False
    return True

def ans():
    for i in range(num):
        if check(i):
            return i+1
    return -1

outFile.write(str(ans()))
inFile.close()
outFile.close()

