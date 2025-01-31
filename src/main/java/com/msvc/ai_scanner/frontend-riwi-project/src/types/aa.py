def contar_votos(candidatos, votos):
    conteo = [] * len(candidatos)
    
    for voto in votos:
        if voto in candidatos: 
            indice = candidatos.index(voto)
            conteo[indice] += 1
    
    max_votos = max(conteo)
    
    ganadores = [candidatos[i] for i in range(len(candidatos)) if conteo[i] == max_votos]
    
    return conteo, ganadores


candidatos = []
num_candidatos = int(input("Ingrese el número de candidatos: "))

for i in range(num_candidatos):
    nombre = input("Ingrese el nombre del candidato {i+1}: ")
    candidatos.append(nombre)

votos = []
num_votos = int(input("Ingrese el número de votos: "))

for i in range(num_votos):
    voto = input(f"Ingrese el voto {i+1}: ")
    votos.append(voto)

resultado, ganadores = contar_votos(candidatos, votos)

print("Resultados:")
for i in range(len(candidatos)):
    print(f"{candidatos[i]}: {resultado[i]} votos")

print("\nGanador(es):", ", ".join(ganadores))
