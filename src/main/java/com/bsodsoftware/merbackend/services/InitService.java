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
		}
		
		long count3 = redService.countSubcategorias();
		if (count3 == 0) {
			Red numeros = redService.findRedByNombre("Números");
			Red campoAditivo = redService.findRedByNombre("Campo Aditivo");
			Red campoMultiplicativo = redService.findRedByNombre("Campo Multiplicativo");
			Red patrones = redService.findRedByNombre("Patrones y Álgebra");
			Red medicion = redService.findRedByNombre("Medición");
			Red geometria = redService.findRedByNombre("Geometría");
			Red datos = redService.findRedByNombre("Datos y Probabilidades");
			// subcategorías
			SubcategoriaRed a1 = new SubcategoriaRed("Reconocer, representar y modelar situaciones que involucran adiciones y sustracciones", campoAditivo);
			a1 = redService.saveSubcategoria(a1);
			SubcategoriaRed a2 = new SubcategoriaRed("Fundamentar y aplicar propiedades", campoAditivo);
			a2 = redService.saveSubcategoria(a2);
			SubcategoriaRed a3 = new SubcategoriaRed("Calcular mentalmente", campoAditivo);
			a3 = redService.saveSubcategoria(a3);
			SubcategoriaRed a4 = new SubcategoriaRed("Calcular de manera escrita", campoAditivo);
			a4 = redService.saveSubcategoria(a4);
			SubcategoriaRed a5 = new SubcategoriaRed("Resolver Problemas", campoAditivo);
			a5 = redService.saveSubcategoria(a5);
			
			SubcategoriaRed m1 = new SubcategoriaRed("Reconocer, representar y modelar situaciones que involucran multiplicaciones y divisionres", campoMultiplicativo);
			m1 = redService.saveSubcategoria(m1);
			SubcategoriaRed m2 = new SubcategoriaRed("Fundamentar y aplicar propiedades", campoMultiplicativo);
			m2 = redService.saveSubcategoria(m2);
			SubcategoriaRed m3 = new SubcategoriaRed("Calcular mentalmente", campoMultiplicativo);
			m3 = redService.saveSubcategoria(m3);
			SubcategoriaRed m4 = new SubcategoriaRed("Calcular de manera escrita", campoMultiplicativo);
			m4 = redService.saveSubcategoria(m4);
			SubcategoriaRed m5 = new SubcategoriaRed("Resolver Problemas", campoMultiplicativo);
			m5 = redService.saveSubcategoria(m5);
			
			SubcategoriaRed p1 = new SubcategoriaRed("Reconocer, describir, continuar y completar patrones", patrones);
			p1 = redService.saveSubcategoria(p1);
			SubcategoriaRed p2 = new SubcategoriaRed("Representar y modelar situaciones mediantes ecuaciones e inecuaciones", patrones);
			p2 = redService.saveSubcategoria(p2);
			SubcategoriaRed p3 = new SubcategoriaRed("Resolver ecuaciones e inecuaciones", patrones);
			p3 = redService.saveSubcategoria(p3);
			SubcategoriaRed p4 = new SubcategoriaRed("Resolver Problemas", patrones);
			p4 = redService.saveSubcategoria(p4);
			
			SubcategoriaRed me1 = new SubcategoriaRed("Identificar y caracterizar atributos de objetos (o eventos) que definen una magnitud", medicion);
			me1 = redService.saveSubcategoria(me1);
			SubcategoriaRed me2 = new SubcategoriaRed("Comparar  y ordenar directa o indirectamente, cantidades de magnitud", medicion);
			me2 = redService.saveSubcategoria(me2);
			SubcategoriaRed me3 = new SubcategoriaRed("Establecer unidades de medida no convencionales y convencionales, adecuadas frente a una situación y realizar transformaciones si fuera necesario", medicion);
			me3 = redService.saveSubcategoria(me3);
			SubcategoriaRed me4 = new SubcategoriaRed("Medir, producir y representar cantidades de magnitud", medicion);
			me4 = redService.saveSubcategoria(me4);
			SubcategoriaRed me5 = new SubcategoriaRed("Calcular y estimar cantidades de magnitud", medicion);
			me5 = redService.saveSubcategoria(me5);
			SubcategoriaRed me6 = new SubcategoriaRed("Resolver problemas", medicion);
			me6 = redService.saveSubcategoria(me6);
			
			SubcategoriaRed d1 = new SubcategoriaRed("Recolectar, organizar y registrar datos en tablas", datos);
			d1 = redService.saveSubcategoria(d1);
			SubcategoriaRed d2 = new SubcategoriaRed("Representar datos mediante gráficos", datos);
			d2 = redService.saveSubcategoria(d2);
			SubcategoriaRed d3 = new SubcategoriaRed("Leer, interpretar y comunicar información a partir de datos, tablas y gráficos", datos);
			d3 = redService.saveSubcategoria(d3);
			SubcategoriaRed d4 = new SubcategoriaRed("Describir, analizar y conjeturar resultados de experimentos aleatorios", datos);
			d4 = redService.saveSubcategoria(d4);
			SubcategoriaRed d5 = new SubcategoriaRed("Resolver Problemas", datos);
			d5 = redService.saveSubcategoria(d5);
			
			SubcategoriaRed n1 = new SubcategoriaRed("Leer, escribir y representar números naturales,  decimales, fracciones y números mixtos", numeros);
			n1 = redService.saveSubcategoria(n1);
			SubcategoriaRed n2 = new SubcategoriaRed("Fundamentar y aplicar propiedades del Sistema de Numeración Decimal, fracciones y números mixtos", numeros);
			n2 = redService.saveSubcategoria(n2);
			SubcategoriaRed n3 = new SubcategoriaRed("Comparar y ordenar números, fracciones y decimales", numeros);
			n3 = redService.saveSubcategoria(n3);
			SubcategoriaRed n4 = new SubcategoriaRed("Estimar y redondear cantidades", numeros);
			n4 = redService.saveSubcategoria(n4);
			SubcategoriaRed n5 = new SubcategoriaRed("Establecer y calcular equivalencias entre fracciones, números mixtos y decimales", numeros);
			n5 = redService.saveSubcategoria(n5);
			SubcategoriaRed n6 = new SubcategoriaRed("Resolver problemas", numeros);
			n6 = redService.saveSubcategoria(n6);
			
			SubcategoriaRed g1 = new SubcategoriaRed("Reconocer, describir y representar  objetos  y movimientos en el espacio", geometria);
			g1 = redService.saveSubcategoria(g1);
			SubcategoriaRed g2 = new SubcategoriaRed("Fundamentar y aplicar propiedades de líneas, figuras y cuerpos y movimientos", geometria);
			g2 = redService.saveSubcategoria(g2);
			SubcategoriaRed g3 = new SubcategoriaRed("Construir, dibujar, comparar y clasificar llíneas, figuras y cuerpos geométricos", geometria);
			g3 = redService.saveSubcategoria(g3);
			SubcategoriaRed g4 = new SubcategoriaRed("Realizar , dibujar y caracterizar traslaciones, rotaciones y reflexiones", geometria);
			g4 = redService.saveSubcategoria(g4);
			SubcategoriaRed g5 = new SubcategoriaRed("Resolver problemas", geometria);
			g5 = redService.saveSubcategoria(g5);
		}
		
		long count4 = nivelService.count();
		if (count4 == 0) {
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
