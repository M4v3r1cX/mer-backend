package com.bsodsoftware.merbackend.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import com.bsodsoftware.merbackend.jpa.entities.Libro;
import com.bsodsoftware.merbackend.jpa.entities.Nivel;
import com.bsodsoftware.merbackend.jpa.entities.Red;
import com.bsodsoftware.merbackend.jpa.entities.SubcategoriaRed;

@Service
public class InitService {
	
	@Autowired
	LibroService libroService;
	
	@Autowired
	RedService redService;
	
	@Autowired
	NivelService nivelService;

	@EventListener(ContextRefreshedEvent.class)
	public void initData() {
		long count = libroService.count();
		if (count == 0) {
			List<Libro> libros = new ArrayList<Libro>();
			libros.add(new Libro("Sumo Primero",""));
			libros.add(new Libro("Cuaderno Rural",""));
			libros.add(new Libro("Clase Publica",""));
			libros.add(new Libro("Juego",""));
			libroService.save(libros);
		}
		
		long count2 = redService.count();
		if (count2 == 0) {
			// redes
			Red numeros = new Red("Números","");
			numeros = redService.save(numeros);
			Red campoAditivo = new Red("Campo Aditivo","");
			campoAditivo = redService.save(campoAditivo);
			Red campoMultiplicativo = new Red("Campo Multiplicativo","");
			campoMultiplicativo = redService.save(campoMultiplicativo);
			Red patrones = new Red("Patrones y Álgebra","");
			patrones = redService.save(patrones);
			Red medicion = new Red("Medición","");
			medicion = redService.save(medicion);
			Red geometria = new Red("Geometría","");
			geometria = redService.save(geometria);
			Red datos = new Red("Datos y Probabilidades","");
			datos = redService.save(datos);
			
			// subcategorías
			SubcategoriaRed a1 = new SubcategoriaRed("Reconocer, representar y modelar situaciones que involucran adiciones y sustracciones", campoAditivo);
			a1 = redService.saveSubcategoria(a1);
			campoAditivo.addSubcategoriaRed(a1);
			SubcategoriaRed a2 = new SubcategoriaRed("Fundamentar y aplicar propiedades", campoAditivo);
			a2 = redService.saveSubcategoria(a2);
			campoAditivo.addSubcategoriaRed(a2);
			SubcategoriaRed a3 = new SubcategoriaRed("Calcular mentalmente", campoAditivo);
			a3 = redService.saveSubcategoria(a3);
			campoAditivo.addSubcategoriaRed(a3);
			SubcategoriaRed a4 = new SubcategoriaRed("Calcular de manera escrita", campoAditivo);
			a4 = redService.saveSubcategoria(a4);
			campoAditivo.addSubcategoriaRed(a4);
			SubcategoriaRed a5 = new SubcategoriaRed("Resolver Problemas", campoAditivo);
			a5 = redService.saveSubcategoria(a5);
			campoAditivo.addSubcategoriaRed(a5);
			campoAditivo = redService.save(campoAditivo);
			
			SubcategoriaRed m1 = new SubcategoriaRed("Reconocer, representar y modelar situaciones que involucran multiplicaciones y divisionres", campoMultiplicativo);
			m1 = redService.saveSubcategoria(m1);
			campoMultiplicativo.addSubcategoriaRed(m1);
			SubcategoriaRed m2 = new SubcategoriaRed("Fundamentar y aplicar propiedades", campoMultiplicativo);
			m2 = redService.saveSubcategoria(m2);
			campoMultiplicativo.addSubcategoriaRed(m2);
			SubcategoriaRed m3 = new SubcategoriaRed("Calcular mentalmente", campoMultiplicativo);
			m3 = redService.saveSubcategoria(m3);
			campoMultiplicativo.addSubcategoriaRed(m3);
			SubcategoriaRed m4 = new SubcategoriaRed("Calcular de manera escrita", campoMultiplicativo);
			m4 = redService.saveSubcategoria(m4);
			campoMultiplicativo.addSubcategoriaRed(m4);
			SubcategoriaRed m5 = new SubcategoriaRed("Resolver Problemas", campoMultiplicativo);
			m5 = redService.saveSubcategoria(m5);
			campoMultiplicativo.addSubcategoriaRed(m5);
			campoMultiplicativo = redService.save(campoMultiplicativo);
			
			SubcategoriaRed p1 = new SubcategoriaRed("Reconocer, describir, continuar y completar patrones", patrones);
			p1 = redService.saveSubcategoria(p1);
			patrones.addSubcategoriaRed(p1);
			SubcategoriaRed p2 = new SubcategoriaRed("Representar y modelar situaciones mediantes ecuaciones e inecuaciones", patrones);
			p2 = redService.saveSubcategoria(p2);
			patrones.addSubcategoriaRed(p2);
			SubcategoriaRed p3 = new SubcategoriaRed("Resolver ecuaciones e inecuaciones", patrones);
			p3 = redService.saveSubcategoria(p3);
			patrones.addSubcategoriaRed(p3);
			SubcategoriaRed p4 = new SubcategoriaRed("Resolver Problemas", patrones);
			p4 = redService.saveSubcategoria(p4);
			patrones.addSubcategoriaRed(p4);
			patrones = redService.save(patrones);
			
			SubcategoriaRed me1 = new SubcategoriaRed("Identificar y caracterizar atributos de objetos (o eventos) que definen una magnitud", medicion);
			me1 = redService.saveSubcategoria(me1);
			medicion.addSubcategoriaRed(me1);
			SubcategoriaRed me2 = new SubcategoriaRed("Comparar  y ordenar directa o indirectamente, cantidades de magnitud", medicion);
			me2 = redService.saveSubcategoria(me2);
			medicion.addSubcategoriaRed(me2);
			SubcategoriaRed me3 = new SubcategoriaRed("Establecer unidades de medida no convencionales y convencionales, adecuadas frente a una situación y realizar transformaciones si fuera necesario", medicion);
			me3 = redService.saveSubcategoria(me3);
			medicion.addSubcategoriaRed(me3);
			SubcategoriaRed me4 = new SubcategoriaRed("Medir, producir y representar cantidades de magnitud", medicion);
			me4 = redService.saveSubcategoria(me4);
			medicion.addSubcategoriaRed(me4);
			SubcategoriaRed me5 = new SubcategoriaRed("Calcular y estimar cantidades de magnitud", medicion);
			me5 = redService.saveSubcategoria(me5);
			medicion.addSubcategoriaRed(me5);
			SubcategoriaRed me6 = new SubcategoriaRed("Resolver problemas", medicion);
			me6 = redService.saveSubcategoria(me6);
			medicion.addSubcategoriaRed(me6);
			medicion = redService.save(medicion);
			
			SubcategoriaRed d1 = new SubcategoriaRed("Recolectar, organizar y registrar datos en tablas", datos);
			d1 = redService.saveSubcategoria(d1);
			datos.addSubcategoriaRed(d1);
			SubcategoriaRed d2 = new SubcategoriaRed("Representar datos mediante gráficos", datos);
			d2 = redService.saveSubcategoria(d2);
			datos.addSubcategoriaRed(d2);
			SubcategoriaRed d3 = new SubcategoriaRed("Leer, interpretar y comunicar información a partir de datos, tablas y gráficos", datos);
			d3 = redService.saveSubcategoria(d3);
			datos.addSubcategoriaRed(d3);
			SubcategoriaRed d4 = new SubcategoriaRed("Describir, analizar y conjeturar resultados de experimentos aleatorios", datos);
			d4 = redService.saveSubcategoria(d4);
			datos.addSubcategoriaRed(d4);
			SubcategoriaRed d5 = new SubcategoriaRed("Resolver Problemas", datos);
			d5 = redService.saveSubcategoria(d5);
			datos.addSubcategoriaRed(d5);
			datos = redService.save(datos);
			
			SubcategoriaRed n1 = new SubcategoriaRed("Leer, escribir y representar números naturales,  decimales, fracciones y números mixtos", numeros);
			n1 = redService.saveSubcategoria(n1);
			numeros.addSubcategoriaRed(n1);
			SubcategoriaRed n2 = new SubcategoriaRed("Fundamentar y aplicar propiedades del Sistema de Numeración Decimal, fracciones y números mixtos", numeros);
			n2 = redService.saveSubcategoria(n2);
			numeros.addSubcategoriaRed(n2);
			SubcategoriaRed n3 = new SubcategoriaRed("Comparar y ordenar números, fracciones y decimales", numeros);
			n3 = redService.saveSubcategoria(n3);
			numeros.addSubcategoriaRed(n3);
			SubcategoriaRed n4 = new SubcategoriaRed("Estimar y redondear cantidades", numeros);
			n4 = redService.saveSubcategoria(n4);
			numeros.addSubcategoriaRed(n4);
			SubcategoriaRed n5 = new SubcategoriaRed("Establecer y calcular equivalencias entre fracciones, números mixtos y decimales", numeros);
			n5 = redService.saveSubcategoria(n5);
			numeros.addSubcategoriaRed(n5);
			SubcategoriaRed n6 = new SubcategoriaRed("Resolver problemas", numeros);
			n6 = redService.saveSubcategoria(n6);
			numeros.addSubcategoriaRed(n6);
			numeros = redService.save(numeros);
			
			SubcategoriaRed g1 = new SubcategoriaRed("Reconocer, describir y representar  objetos  y movimientos en el espacio", geometria);
			g1 = redService.saveSubcategoria(g1);
			geometria.addSubcategoriaRed(g1);
			SubcategoriaRed g2 = new SubcategoriaRed("Fundamentar y aplicar propiedades de líneas, figuras y cuerpos y movimientos", geometria);
			g2 = redService.saveSubcategoria(g2);
			geometria.addSubcategoriaRed(g2);
			SubcategoriaRed g3 = new SubcategoriaRed("Construir, dibujar, comparar y clasificar llíneas, figuras y cuerpos geométricos", geometria);
			g3 = redService.saveSubcategoria(g3);
			geometria.addSubcategoriaRed(g3);
			SubcategoriaRed g4 = new SubcategoriaRed("Realizar , dibujar y caracterizar traslaciones, rotaciones y reflexiones", geometria);
			g4 = redService.saveSubcategoria(g4);
			geometria.addSubcategoriaRed(g4);
			SubcategoriaRed g5 = new SubcategoriaRed("Resolver problemas", geometria);
			g5 = redService.saveSubcategoria(g5);
			geometria.addSubcategoriaRed(g5);
			geometria = redService.save(geometria);
		}
		
		long count3 = nivelService.count();
		if (count3 == 0) {
			List<Nivel> niveles = new ArrayList<Nivel>();
			niveles.add(new Nivel(1L, "1"));
			niveles.add(new Nivel(2L, "2"));
			niveles.add(new Nivel(3L, "3"));
			niveles.add(new Nivel(4L, "4"));
			niveles.add(new Nivel(5L, "5"));
			niveles.add(new Nivel(6L, "6"));
			nivelService.saveAll(niveles);
		}
	}
}
