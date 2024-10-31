import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CleaningServices
import androidx.compose.material.icons.filled.Eco
import androidx.compose.material.icons.filled.LocalCafe
import androidx.compose.material.icons.filled.LocalDrink
import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.nexoclub.R
import com.example.nexoclub.ui.theme.buttonGreen

data class Product(
    val name: String,
    val description: String,
    val price: String,
    val icon: ImageVector
)

@Composable
fun HomeScreen() {

    val destaque = listOf(
        Product("Água de Coco 1L", "Bebida refrescante e saudável.", "R$ 4,99", Icons.Default.LocalDrink),
        Product("Detergente Neutro 500ml", "Ideal para lavar louças.", "R$ 2,49", Icons.Default.CleaningServices)
    )

    val carouselBebidas = listOf(
        Product("Refrigerante 2L", "Refrigerante de cola.", "R$ 5,99", Icons.Default.LocalDrink),
        Product("Suco de Laranja 1L", "Suco natural de laranja.", "R$ 6,49", Icons.Default.LocalDrink),
        Product("Cerveja Lata 350ml", "Cerveja premium.", "R$ 2,99", Icons.Default.LocalDrink),
        Product("Água Mineral 500ml", "Água mineral sem gás.", "R$ 1,49", Icons.Default.LocalDrink),
        Product("Vinho Tinto 750ml", "Vinho tinto seco.", "R$ 24,99", Icons.Default.LocalDrink)
    )

    val carouselAlimentosFrescos = listOf(
        Product("Maçã Gala (kg)", "Frutas frescas.", "R$ 4,99", Icons.Default.Eco),
        Product("Banana Nanica (kg)", "Banana madura.", "R$ 3,99", Icons.Default.Eco),
        Product("Tomate Italiano (kg)", "Ideal para saladas.", "R$ 5,49", Icons.Default.Eco),
        Product("Cenoura (kg)", "Cenoura orgânica.", "R$ 2,99", Icons.Default.Eco),
        Product("Alface Crespa (un)", "Alface crocante.", "R$ 1,99", Icons.Default.Eco)
    )

    val carouselCarnes = listOf(
        Product("Filé de Frango (kg)", "Cortes frescos de frango.", "R$ 14,99", Icons.Default.Restaurant),
        Product("Carne Moída (kg)", "Carne bovina moída.", "R$ 21,99", Icons.Default.Restaurant),
        Product("Picanha (kg)", "Picanha bovina.", "R$ 54,99", Icons.Default.Restaurant),
        Product("Coxa de Frango (kg)", "Coxa de frango.", "R$ 9,99", Icons.Default.Restaurant),
        Product("Costela Bovina (kg)", "Costela para churrasco.", "R$ 29,99", Icons.Default.Restaurant)
    )

    val carouselLaticinios = listOf(
        Product("Leite Integral 1L", "Leite integral.", "R$ 3,49", Icons.Default.LocalCafe),
        Product("Iogurte Natural 170g", "Iogurte saudável.", "R$ 2,29", Icons.Default.LocalCafe),
        Product("Queijo Mussarela (kg)", "Queijo fresco.", "R$ 25,99", Icons.Default.LocalCafe),
        Product("Requeijão 250g", "Requeijão cremoso.", "R$ 5,99", Icons.Default.LocalCafe),
        Product("Manteiga 200g", "Manteiga com sal.", "R$ 7,99", Icons.Default.LocalCafe)
    )

    val carouselLimpeza = listOf(
        Product("Sabão em Pó 1kg", "Sabão em pó multiuso.", "R$ 8,99", Icons.Default.CleaningServices),
        Product("Desinfetante 2L", "Desinfetante perfumado.", "R$ 6,49", Icons.Default.CleaningServices),
        Product("Amaciante 2L", "Amaciante concentrado.", "R$ 7,99", Icons.Default.CleaningServices),
        Product("Detergente Neutro 500ml", "Detergente para louças.", "R$ 2,49", Icons.Default.CleaningServices),
        Product("Esponja de Aço", "Esponja multiuso.", "R$ 1,99", Icons.Default.CleaningServices)
    )

    val carouselMercearia = listOf(
        Product("Arroz 5kg", "Arroz branco longo fino.", "R$ 18,99", Icons.Default.ShoppingCart),
        Product("Feijão Carioca 1kg", "Feijão de alta qualidade.", "R$ 6,99", Icons.Default.ShoppingCart),
        Product("Macarrão Espaguete 500g", "Massa de trigo.", "R$ 3,99", Icons.Default.ShoppingCart),
        Product("Óleo de Soja 900ml", "Óleo de soja.", "R$ 5,49", Icons.Default.ShoppingCart),
        Product("Farinha de Trigo 1kg", "Farinha de trigo refinada.", "R$ 4,49", Icons.Default.ShoppingCart)
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFF003366))
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(R.drawable.nexo_logo),
                contentDescription = null,
                contentScale = ContentScale.Fit
            )
        }
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 10.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            item {
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Bem vindo(a) ao app do NEXOClub! Confira as nossas ofertas",
                    textAlign = TextAlign.Center
                )
            }

            // Três produtos em destaque que preenchem a tela
            items(destaque) { product ->
                HighlightedProductCard(product)
            }

            item {
                Spacer(modifier = Modifier.height(16.dp))
                // Alimentos Frescos
                Text(
                    text = "Alimentos Frescos",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 18.sp
                )
                Text(
                    text = "Frutas, legumes e verduras frescos, selecionados com o maior cuidado."
                )
                Spacer(modifier = Modifier.height(8.dp))
                ProductCarousel(carouselAlimentosFrescos) // Seu carrossel de produtos
                Spacer(modifier = Modifier.height(16.dp))
            }

            item {
                // Bebidas
                Text(
                    text = "Bebidas",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 18.sp
                )
                Text(
                    text = "Diversas opções de bebidas para todas as ocasiões, incluindo descontos especiais."
                )
                Spacer(modifier = Modifier.height(8.dp))
                ProductCarousel(carouselBebidas)
                Spacer(modifier = Modifier.height(16.dp))
            }

            item {
                // Carnes e Aves
                Text(
                    text = "Carnes e Aves",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 18.sp
                )
                Text(
                    text = "Carnes e aves de alta qualidade, frescas e selecionadas."
                )
                Spacer(modifier = Modifier.height(8.dp))
                ProductCarousel(carouselCarnes)
                Spacer(modifier = Modifier.height(16.dp))
            }

            item {
                // Laticínios
                Text(
                    text = "Laticínios",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 18.sp
                )
                Text(
                    text = "Queijos, iogurtes, manteigas e outros produtos laticínios frescos e saborosos."
                )
                Spacer(modifier = Modifier.height(8.dp))
                ProductCarousel(carouselLaticinios)
                Spacer(modifier = Modifier.height(16.dp))
            }

            item {
                // Produtos de Limpeza
                Text(
                    text = "Produtos de Limpeza",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 18.sp
                )
                Text(
                    text = "Tudo para manter sua casa limpa e organizada, com os melhores produtos."
                )
                Spacer(modifier = Modifier.height(8.dp))
                ProductCarousel(carouselLimpeza)
                Spacer(modifier = Modifier.height(16.dp))
            }

            item {
                // Mercearia
                Text(
                    text = "Mercearia",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 18.sp
                )
                Text(
                    text = "Uma variedade de produtos básicos para o seu dia a dia, incluindo grãos, massas e enlatados."
                )
                Spacer(modifier = Modifier.height(8.dp))
                ProductCarousel(carouselMercearia)
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}

@Composable
fun HighlightedProductCard(product: Product) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp),
        elevation = CardDefaults.cardElevation(8.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardColors(
            containerColor = Color(0xFF003366),
            contentColor = Color.White,
            disabledContentColor = Color.Transparent,
            disabledContainerColor = Color.Transparent
        )
    ) {
        Row(
            modifier = Modifier
                .padding(vertical = 16.dp, horizontal = 28.dp)
                .fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Column(
                modifier = Modifier.weight(3f),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = product.name,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    textAlign = TextAlign.Start
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = product.description,
                    fontWeight = FontWeight.Normal,
                    fontSize = 14.sp,
                    textAlign = TextAlign.Start
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = product.price,
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 22.sp,
                    textAlign = TextAlign.Start
                )
            }
            Icon(
                imageVector = product.icon,
                contentDescription = null,
                modifier = Modifier
                    .size(40.dp) // Ajuste do tamanho do ícone
            )
        }
    }
}

@Composable
fun ProductCarousel(products: List<Product>) {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(products) { product ->
            ProductCardCarousel(product)
        }
    }
}

@Composable
fun ProductCardCarousel(product: Product) {
    Card(
        modifier = Modifier
            .width(150.dp)
            .height(100.dp),
        elevation = CardDefaults.cardElevation(0.dp), // Sem sombra
        shape = RoundedCornerShape(16.dp),
        colors = CardColors(
            containerColor = Color(0xFFC2D2FF),
            contentColor = Color.Black,
            disabledContentColor = Color.Transparent,
            disabledContainerColor = Color.Transparent
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = product.icon,
                contentDescription = null,
                modifier = Modifier
                    .size(25.dp) // Ajuste do tamanho do ícone
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = product.name,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = product.price,
                fontWeight = FontWeight.Bold,
                fontSize = 22.sp,
                textAlign = TextAlign.Center
            )
        }
    }
}
