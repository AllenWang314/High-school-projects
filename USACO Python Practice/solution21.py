#test = open("buckets.in", "w")
#test.write("..........\n..........\n..........\n..B.......\
#\n..........\n.....R....\n..........\n..........\n.....L....\n..........")
#test.close()

inFile = open("buckets.in", "r")
outFile =open("buckets.out", "w")

lines = []
B = [0,0]
L = [0,0]
for i in range(10):
    line = inFile.readline()
    if ("L" in line):
        L = [i,line.index("L")]
    if ("B" in line):
        B = [i,line.index("B")]   
    lines.append(line)

dist = []
visited = []
for i in range(10):
    visited.append([])
    dist.append([])
    for j in range(10):
        visited[i].append(False)
        dist[i].append(30)

queue = [[B[0],B[1]]]

def flood(a,b):
    if lines[a][b] == "R":
        visited[a][b] = True
    if not visited[a][b]:
        visited[a][b] = True
        if a > 0 and lines[a-1][b] != "R":
            dist[a-1][b] = min(dist[a-1][b], dist[a][b]+1)
            dist[a][b] = min(dist[a-1][b] + 1, dist[a][b])
            queue.append([a-1,b])
        if a < 9 and lines[a+1][b] != "R":
            dist[a+1][b] = min(dist[a+1][b], dist[a][b]+1)
            dist[a][b] = min(dist[a+1][b] + 1, dist[a][b])
            queue.append([a+1,b])
        if b > 0 and lines[a][b-1] != "R":
            dist[a][b-1] = min(dist[a][b-1], dist[a][b]+1)
            dist[a][b] = min(dist[a][b-1] + 1, dist[a][b])
            queue.append([a,b-1])
        if b < 9 and lines[a][b+1] != "R":
            dist[a][b+1] = min(dist[a][b+1], dist[a][b]+1)
            dist[a][b] = min(dist[a][b+1] + 1, dist[a][b])
            queue.append([a,b+1])

dist[B[0]][B[1]] = 0

while(len(queue) != 0):
    thing = queue.pop()
    flood(thing[0], thing[1])


outFile.write(str(dist[L[0]][L[1]]-1))
inFile.close()
outFile.close()
