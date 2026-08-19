<div align="center">

# woodshops — Distribution Artefacts

**Packaged JAR and generated Javadoc for the WoodShops timber retail system. The source code lives in a separate repository.**

[![Java](https://img.shields.io/badge/Java-SE-ED8B00?logo=openjdk&logoColor=white)](https://openjdk.org/)
[![Artefacts](https://img.shields.io/badge/Contents-JAR%20%2B%20Javadoc-informational)](#whats-in-here)
[![Source](https://img.shields.io/badge/Source-AA5--FP056--__WoodShops-important)](https://github.com/dadd86/AA5-FP056-_WoodShops)
[![Javadoc](https://img.shields.io/badge/Javadoc-19%20types-orange)](Documentacion/index.html)
[![Course](https://img.shields.io/badge/FP056-CFGS%20DAM-blueviolet)](#about-this-project)

[Architecture (EN)](ARCHITECTURE.md) · [Arquitectura (ES)](docs/es/ARCHITECTURE.md) · [Javadoc](Documentacion/index.html)

</div>

---

> ### Looking for the source code?
>
> **It is not in this repository.** This one holds only the packaged JAR, the generated Javadoc, the assignment PDF and a demonstration video.
>
> The Java source is at **[dadd86/AA5-FP056-_WoodShops](https://github.com/dadd86/AA5-FP056-_WoodShops)** — 18 source files under `src/aa4_woodshops/`, with an Ant build.

---

## What's in here

| Path | Contents |
| --- | --- |
| `Ejecutable/` | Runnable JAR, 69 KB, no external dependencies |
| `Documentacion/` | Generated Javadoc — 65 HTML files covering 19 types |
| `AA4(FP056)_…pdf` | Assignment deliverable |
| `AA4(FP056)_…zip` | Archived copy of the project |
| `W10DAM_…mp4` | Demonstration recording |
| `.project` | Eclipse descriptor (empty `buildSpec` and `natures` — it cannot build anything) |

**Zero `.java` files.** Verified by recursive search across the whole repository.

---

## The application these artefacts belong to

WoodShops is a console-based retail management system for a timber and carpentry supplies business, written in Java SE with no external dependencies.

It handles a product catalogue across three categories, a warehouse indexed by product code, a supplier registry, two customer types with different pricing, and a sales process producing itemised tickets with automatic discount application.

The program is worth reading, and the reasons are in the source repository rather than here:

- **`BigDecimal` for every monetary value**, with explicit scale and `RoundingMode.HALF_UP` — avoiding both the floating-point money trap and the `ArithmeticException` that non-terminating decimals throw without an explicit rounding mode.
- **Fail-fast constructor validation**, so a `Producto` cannot exist in an invalid state.
- **A correct `equals`/`hashCode` contract** using `getClass()` rather than `instanceof`, which is what makes the `HashMap`-backed warehouse behave.
- **Immutable financial records** — a completed `Venta` cannot be silently rewritten.
- **A lazily computed total** with explicit cache invalidation.

Full analysis in [`ARCHITECTURE.md`](ARCHITECTURE.md), and the evidence-based specification derived from the actual source in the [source repository's `ARCHITECTURE.md`](https://github.com/dadd86/AA5-FP056-_WoodShops/blob/master/ARCHITECTURE.md).

---

## Prerequisites

| Requirement | Version | Notes |
| --- | --- | --- |
| JRE or JDK | 8 or newer | Only needed to run the JAR |
| Web browser | any | To read the Javadoc |

---

## Quickstart

```bash
git clone https://github.com/dadd86/woodshops.git
cd woodshops
java -jar "Ejecutable/AA4(FP056)_DiazDevia_DiegoArmando.jar"
```

The console menu appears. Note that the repository is around 61 MB because of a committed demonstration video.

---

## Working with the artefacts

Read the documentation — `overview-tree.html` is the single most informative page, rendering the full inheritance tree at a glance:

```bash
# macOS
open Documentacion/index.html
# Linux
xdg-open Documentacion/index.html
# Windows
start Documentacion\index.html
```

Inspect the JAR without extracting it:

```bash
unzip -p "Ejecutable/AA4(FP056)_DiazDevia_DiegoArmando.jar" META-INF/MANIFEST.MF
unzip -l  "Ejecutable/AA4(FP056)_DiazDevia_DiegoArmando.jar"
```

Extract the archived project:

```bash
unzip "AA4(FP056)_DiazDevia_DiegoArmando.zip" -d extracted/
```

Get the real source instead:

```bash
git clone https://github.com/dadd86/AA5-FP056-_WoodShops.git
```

---

## Documented type structure

```text
Producto (abstract)                    Cliente (abstract)
├── Tablero    + altura, anchura,      ├── ClienteProfesional  + descuento
│                tipoTablero           └── ClienteWoodFriend   + codigoSocio
├── Barniz     + mililitros, color
└── Articulo   + tipoArticulo

Proveedor · Almacen · Tienda · Venta · DetalleVenta · GestorProveedores
TipoTablero · ColorBarniz · TipoArticulo          (enumerations)
AA4_WoodShops · AA4_WoodShops.TipoProductoUtil    (entry point)
```

Nineteen documented types in package `aa4_woodshops`. Class diagram in [`ARCHITECTURE.md` §2.2](ARCHITECTURE.md#22-type-structure).

---

## Directory tree

```text
woodshops/
├── Documentacion/                              # Generated Javadoc
│   ├── index.html                               #   entry point
│   ├── overview-tree.html                       #   inheritance tree — start here
│   ├── allclasses-index.html
│   ├── aa4_woodshops/                           #   per-type documentation, 19 types
│   ├── index-files/                             #   alphabetical index
│   ├── legal/                                   #   JDK Javadoc boilerplate
│   ├── element-list                             #   enables javadoc -link
│   └── *.js                                     #   client-side search indices
├── Ejecutable/
│   └── AA4(FP056)_DiazDevia_DiegoArmando.jar    # 69 KB, runnable
├── ARCHITECTURE.md                              # Architecture specification (English)
├── docs/es/ARCHITECTURE.md                      # Architecture specification (Spanish)
├── AA4(FP056)_DiazDevia_DiegoArmando.pdf        # Assignment deliverable
├── AA4(FP056)_DiazDevia_DiegoArmando.zip        # Archived project
├── .project                                     # Eclipse descriptor, no Java nature
└── W10DAM_20231211 [Corriendo] - ...mp4         # Demonstration recording
```

---

## Documentation

| Document | Language | Contents |
| --- | --- | --- |
| [`ARCHITECTURE.md`](ARCHITECTURE.md) | English | What this repository holds, the documented architecture, artefact inventory, consolidation recommendation |
| [`docs/es/ARCHITECTURE.md`](docs/es/ARCHITECTURE.md) | Spanish | 1:1 equivalent of the above |
| [`Documentacion/index.html`](Documentacion/index.html) | Spanish | Generated API documentation |

---

## A note on this repository's future

Two repositories under this account hold the same project — roughly 157 MB of storage for one program of 388 KB of source, with the code visible in only one of them. A visitor clicking `woodshops`, the more natural-looking name of the two, finds generated HTML and a JAR with no source to read.

The recommendation in [`ARCHITECTURE.md` §5](ARCHITECTURE.md#5-consolidation-recommendation) is to **archive or delete this repository** and consolidate on the source one, renamed to something descriptive. Everything here is either duplicated there or regenerable with `javadoc` and `ant jar`.

Worth knowing: Git retains every committed blob permanently, so deleting the video in a later commit does not shrink the repository. Rewriting history or starting fresh are the only remedies — and both are far easier on a small academic project now than on a larger one later.

---

## About this project

Coursework for module **FP056** of the CFGS in Desarrollo de Aplicaciones Multiplataforma.

Author: **Diego Armando Diaz Devia** — [github.com/dadd86](https://github.com/dadd86)
