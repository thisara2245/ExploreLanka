package com.example.explorelanka


import Attraction
import Location
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class LocationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Get destination name from intent
        val destinationName = intent.getStringExtra("destination_name")

        // Create location data based on destination
        val location = when (destinationName) {
            "Matara" -> Location(
                name = "Matara",
                headerImageResId = R.drawable.matara4,
                attractions = listOf(
                    Attraction("Paravi Dupatha", "Paravi Dupatha is a scenic island located off the coast of Matara, Sri Lanka. Connected by a footbridge, it features a peaceful Buddhist temple surrounded by lush greenery. The island offers a serene environment for meditation and relaxation, attracting both pilgrims and tourists seeking tranquility and natural beauty.", R.drawable.matara1),
                    Attraction("Dondra Point", "Dondra Point, the southernmost tip of Sri Lanka, is renowned for its historic lighthouse and panoramic ocean views. Located near Matara, it's a prime spot for whale watching and coastal exploration. The area is also home to ancient temples, adding cultural and spiritual significance to this beautiful coastal landmark.", R.drawable.mataralighthouse),
                    Attraction("Mirissa", "Mirissa is a popular coastal town in southern Sri Lanka, famous for its stunning beach, turquoise waters, and vibrant nightlife. It's a top destination for whale watching, surfing, and snorkeling. The palm-fringed shoreline and relaxed atmosphere make it ideal for both adventure seekers and those looking to unwind by the sea.", R.drawable.mirissa),
                    Attraction("Hiriketiya Beach", "Hiriketiya Beach is a crescent‑shaped, golden‑sanded bay on Sri Lanka’s southern coast near Dikwella. Backed by lush palm trees and turquoise waters, it offers consistent waves perfect for beginner to intermediate surfers. The beach hosts trendy cafes, surf schools, yoga retreats, and a laid‑back bohemian vibe amid natural beauty.", R.drawable.hiriketiya),
                    Attraction("Star Fort", "Star Fort is a fort situated in Matara. Constructed in 1765 by the Dutch, it is approximately 350m from the gate to the Matara fort.This defence fort built from granite stone and corals in order to protect the main fort of Matara from attacks originating from the Nilwala River. It is built to a unique shape of a six pointed star which is why it is called Star Fort.", R.drawable.starfort),
                    Attraction("Weherahena", "Weherahena Temple, located in Matara, Sri Lanka, is a famous Buddhist temple known for its massive seated Buddha statue and extensive series of colorful wall murals depicting Jataka tales. Built within a large underground tunnel network, it attracts many pilgrims and tourists for its spiritual significance and artistic beauty.", R.drawable.weherahena),
                    Attraction("Polhena Beach", "Polhena Beach, near Matara on Sri Lanka’s southern coast, is a tranquil cove sheltered by an offshore coral reef. Its calm, shallow waters create a natural lagoon ideal for swimming and snorkeling with sea turtles. Framed by golden sand and swaying palms, it offers relaxing vibes, local seafood eateries and peaceful sunsets.", R.drawable.polhena),
                    Attraction("Blue Beach Island", "Blue Beach Island is a secluded tied‑island off Nilwella near Dikwella, southern Sri Lanka. Connected by a sandbar at low tide, it offers serene turquoise waters ideal for snorkeling, camping around a bonfire under swaying palms, and stunning sunrise or sunset views. A tranquil escape for nature lovers.", R.drawable.bluebeach),
                )
            )
            "Kandy" -> Location(
                name = "Kandy",
                headerImageResId = R.drawable.kandy1,
                attractions = listOf(
                    Attraction("Temple of the Tooth Relic", "The Temple of the Tooth Relic, located in Kandy, Sri Lanka, is a sacred Buddhist site that houses a relic of Lord Buddha's tooth. Revered by Buddhists worldwide, the temple is a symbol of cultural and spiritual significance, attracting pilgrims and tourists for its religious rituals and historic architecture.", R.drawable.kandy1),
                    Attraction("Ambuluwawa", "Ambuluwawa, near Gampola in Sri Lanka’s central highlands, is a biodiverse hill sanctuary crowned by a 48m spiral tower atop its 1,100m peak. The multi‑religious complex houses shrines for Buddhism, Hinduism, Islam and Christianity. A climb rewards visitors with sweeping 360° vistas of distant peaks and verdant valleys.", R.drawable.ambulu),
                    Attraction("The Royal Botanical Gardens", "Royal Botanic Gardens, Peradeniya, located just west of Kandy along the Mahaweli River, spans approximately 147 acres at 460m elevation. Home to over 4,000 plant species including orchids, spice trees, palms, medicinal herbs and giant bamboo the gardens feature iconic palm avenues, a great lawn beneath a massive Javan fig, and a rich botanical legacy dating back to 1821", R.drawable.royal),
                    Attraction("Sadagiri Maha Saya", "Sadagiri Maha Seya nestled in the Hanthana Range near Kandy, is the largest stupa in Kandy District. Rising 33m at 930m elevation, its gleaming white dome encloses a spacious inner hall supported by a Bodhi‑tree column. Visitors enjoy sweeping 180° views over misty hills and valleys", R.drawable.sadagirisaya),
                    Attraction("Bahirawakanda Viharaya", "Bahirawakanda Viharaya (Sri Maha Bodhi Viharaya), perched atop Bahirawa Kanda just 2km from Kandy city, is crowned by an 88‑ft white Buddha in meditative Dhyana Mudra. Completed in 1993, the statue offers sweeping panoramic views and a serene spiritual atmosphere. Once a feared “Devil’s Hill,” it now symbolizes peace.", R.drawable.bahirawakanda),
                    Attraction("Lankathilaka Temple", "Lankathilaka Vihara is a magnificent 14th‑century Buddhist temple near Kandy, built atop the natural Panhalgala rock. Commissioned by King Bhuvanekabahu IV, it blends Sinhalese, Dravidian and Indo‑Chinese styles. Its image house once four stories high features elaborate carvings, Kandyan era paintings, Hindu shrines and ancient inscriptions in Sinhala and Tamil", R.drawable.lankatilaka),
                    Attraction("Kandy City View Point", "Kandy City View Point, perched atop Castle Hill near the Garrison Cemetery, offers panoramic vistas of Kandy’s bustling cityscape. From this elevated lookout, visitors can admire the serene Kandy Lake, the Temple of the Tooth, verdant hills and terracotta rooftops. Ideal for photography, sunsets and absorbing Sri Lanka’s cultural heart.", R.drawable.kandyviewpoint),
                    Attraction("Nelligala", "Nelligala International Buddhist Center is a Buddhist temple situated in Muruthalawa village in the Kandy district. Situated on top of a mountain, it is a popular place for sightseeing visitors and tourists due to its sheer beauty and amazing view of the surrounding landscape. It is a modern temple with its construction being commenced in 2015.", R.drawable.nelligala)
                )
            )
            "Galle" -> Location(
                name = "Galle",
                headerImageResId = R.drawable.galle,
                attractions = listOf(
                    Attraction("Jungle Beach", "Jungle Beach, near Unawatuna, is a secluded tropical cove framed by verdant jungle. A short trek through greenery leads to golden sand, calm, reef-protected waters ideal for swimming and snorkeling. A laid-back café, sunbeds, occasional monkeys and birds add charm, making it a serene escape from busier beaches.", R.drawable.junglebeach),
                    Attraction("Hikkaduwa Beach", "The Hikkaduwa Beach is a popular and beautiful beach situated in Hikkduwa. Its crystal clear water allows amazing opportunities for water sports activities such as snorkelling, surfing and fishing. The area is also a popular place for sea turtles and hatching. The Hikkaduwa reef is also popular for its beautiful corals and is a popular tourist destination due to this reason.", R.drawable.hikkaduwa),
                    Attraction("Galle Fort", "Galle Fort, a UNESCO World Heritage Site on Sri Lanka’s southern coast, is a historic 17th-century fortress built by the Dutch. Enclosed by thick stone ramparts, it features colonial architecture, cobbled streets, museums, cafes, and churches. The fort offers ocean views, vibrant culture, and a glimpse into Sri Lanka’s colonial past.", R.drawable.galle),
                    Attraction("Martin Wickramasinghe Museum", "Martin Wickramasinghe Folk Museum, in Koggala, Sri Lanka, is the ancestral home turned literary‑cultural complex of Martin Wickramasinghe. It showcases his birthplace, the ‘Hall of Life’, his ashes, and a rich folk museum displaying traditional crafts, tools, manuscripts and artifacts reflecting rural Sri Lankan heritage rooted in his writings.", R.drawable.martinwickramasinghe),
                    Attraction("Thalpe Beach", "Thalpe Beach is a beach situated in the Southern province of Sri Lanka, close to the city of Galle. It is a popular destination among both locals and foreigners. There are vibrant shops, restaurant and various water sports related activities to do in the surrounding area of Thalpe.", R.drawable.thalpe),
                    Attraction("Galle Harbor", "The Galle Harbor is a natural harbor located in the Southwestern coast of the island. It is the only Sri Lankan port that entertain pleasure yachts and has been recognized as one of the worldâ€™s best attractions for yachting. It is one of the most important harbors of the country and remained in the top position until an artificial harbor was built in Colombo in 1873.", R.drawable.galleharbor),
                    Attraction("Sinharaja Rain Forest", "Sinharaja Rain Forest is a forest reserve and national park which is rich in bio diversity. It is designated as a Biosphere Reserve and UNESCO World Heritage Site. The rain forest lies across the Sabaragamuwa and Southern provinces. Sinharaja boasts of Sri Lankas endemic birds, reptiles, mammals including elephants and leopards.", R.drawable.sinharaja)

                )
            )

            "NuwaraEliya" -> Location(
                name = "NuwaraEliya",
                headerImageResId = R.drawable.nuwaraeliya,
                attractions = listOf(
                    Attraction("Gregory Lake", "Gregory Lake, located in Nuwara Eliya, is a scenic reservoir ideal for boating, jet skiing, and picnicking. Surrounded by lush hills and colonial-style bungalows, the lake offers a peaceful escape with walking paths, horse rides, and cool mountain air, making it a popular family-friendly destination in Sri Lanka’s hill country.", R.drawable.gregory),
                    Attraction("Strawberry Farm", "Strawberry Farms in Nuwara Eliya offer visitors the chance to explore greenhouses, hand-pick fresh strawberries, and enjoy strawberry-based treats like milkshakes and desserts. Nestled in the cool highlands, these farms blend agriculture and tourism, providing a unique and tasty experience amid the region’s scenic mountain landscapes and misty climate.", R.drawable.strawberry),
                    Attraction("NuwaraEliya Post Office", "The Nuwara Eliya Post Office is a charming colonial-era red-brick building built in 1894, showcasing Tudor-style architecture. Still operational, it stands as a symbol of British heritage in Sri Lanka. Tourists often visit to admire its historic charm and send postcards from this iconic hill-country landmark.", R.drawable.nuwaraeliyapostoffice),
                    Attraction("Victoria Park", "Victoria Park, located in the heart of Nuwara Eliya, is a beautifully maintained public garden known for its colorful flower beds, tall trees, and diverse birdlife. Perfect for nature lovers and families, it offers walking trails, a mini train for kids, and a peaceful setting in the cool climate.", R.drawable.victoria),
                    Attraction("Gartmore Falls", "Gartmore Falls, nestled in the Maskeliya region, is a twin waterfall formed by the Maskeliya Oya. Surrounded by tea plantations and forested hills, it offers a serene escape with trekking paths and scenic views. It's a lesser-known gem near Adam’s Peak, ideal for nature photography and hiking enthusiasts.", R.drawable.gartmore),
                    Attraction("Moon Plains", "Moon Plains, near Nuwara Eliya, is a scenic grassland plateau offering panoramic 360-degree views of surrounding mountain ranges including Pidurutalagala. Known as “Mini World's End,” it’s rich in biodiversity, home to deer, leopards, and birdlife. A must-visit for eco-tourism, nature walks, and breathtaking sunrise views over the highlands." , R.drawable.moonplains),
                    Attraction("Mandaram Nuwara", "Mandaram Nuwara, a secluded village at the foot of the Pidurutalagala range, is often called “the village of eternal rain.” Surrounded by misty mountains, waterfalls, and tea estates, it offers cool weather, serene landscapes, and rich biodiversity, making it perfect for off-the-beaten-path travelers and nature seekers.", R.drawable.mandaramnuwara),
                    Attraction("Abewela New Zealand Farm", "Abewela New Zealand Farm, located in Sri Lanka’s central highlands, is a dairy farm known for its lush pastures, Holstein Friesian cows, and cool climate. Visitors can tour the farm, observe milk production, and enjoy scenic views of green fields and rolling hills resembling the landscapes of New Zealand.", R.drawable.abewela),
                )
            )
            "Colombo" -> Location(
                name = "Colombo",
                headerImageResId = R.drawable.colombo1,
                attractions = listOf(
                    Attraction("Galle Face Green", "Galle Face Green is a popular urban park along the Colombo coastline, perfect for evening strolls, kite flying, and street food. Overlooking the Indian Ocean, it offers breathtaking sunsets and a lively atmosphere. Locals and tourists gather here to relax, play, or enjoy iconic treats like isso wade and achcharu.", R.drawable.galleface),
                    Attraction("Viharamahadevi Park", "Viharamahadevi Park, Colombo’s largest and oldest public park, features expansive green lawns, shady trees, flower gardens, and a children's play area. Located opposite the Town Hall, it includes a golden Buddha statue and peaceful walking paths. It's a relaxing urban retreat in the heart of the city for families and nature lovers.", R.drawable.viharamahadewi),
                    Attraction("Kelaniya Raja Maha Viharaya", "Kelaniya Raja Maha Viharaya is an ancient Buddhist temple near Colombo, believed to be visited by the Buddha himself. Renowned for its sacred stupa, vibrant murals, and annual Duruthu Perahera, the temple is a major pilgrimage site, blending spiritual significance with historic and artistic grandeur in a serene riverside setting.", R.drawable.kelaniya),
                    Attraction("Lotus Tower", "The Lotus Tower is South Asia’s tallest tower, standing at 350 meters in Colombo. Shaped like a blooming lotus, it features observation decks with panoramic views, a revolving restaurant, shopping areas, and entertainment zones. It symbolizes modern Sri Lanka and is a major tourist attraction and landmark of Colombo's skyline.", R.drawable.colombo),
                    Attraction("Diyatha Uyana", "Diyatha Uyana is a scenic lakeside park in Battaramulla, offering walking paths, landscaped gardens, food courts, and a weekend floating market. Popular among families and couples, it provides a calm environment for evening strolls or boat rides. The park beautifully combines nature, leisure, and urban development near Parliament grounds.", R.drawable.diyatha),
                    Attraction("One Galle Face Mall", "One Galle Face Mall is Colombo’s premier luxury shopping destination, housing international and local brands, a cinema, food court, and entertainment zones. Located near Galle Face Green, it’s part of the One Galle Face complex with residences and a Shangri-La hotel. It offers a modern shopping and lifestyle experience.", R.drawable.ogf),
                    Attraction("Port City Colombo", "Port City Colombo is a futuristic urban development built on reclaimed land along Colombo’s coast. Designed as a financial hub, it features commercial zones, luxury apartments, a marina, and recreational spaces. Still under development, it aims to transform Sri Lanka into a global business destination with sustainable, smart city infrastructure.", R.drawable.portcity),
                    Attraction("Gangaramaya Temple", "Gangaramaya Temple in Colombo is a vibrant mix of traditional and modern architecture. It features a museum, library, Bodhi tree, and a collection of Buddhist relics and statues. Known for its cultural significance and annual Navam Perahera, the temple is a spiritual and tourist hub reflecting Sri Lanka’s religious heritage." , R.drawable.gangaramaya),
                    Attraction("Havelock City Mall", "Havelock City Mall is a modern shopping and lifestyle center in Colombo featuring fashion outlets, eateries, a cinema, and entertainment zones. It is part of the Havelock City residential complex. The mall offers a premium shopping experience with a mix of international brands and stylish spaces for dining and relaxation.", R.drawable.havelock),
                    Attraction("National Zoological Gardens", "The Dehiwala Zoo, officially the National Zoological Gardens, is one of Asia’s oldest zoos. Located in Dehiwala, it houses a wide variety of animals, birds, reptiles, and marine life. With animal shows and educational programs, it’s a family-friendly attraction that promotes wildlife awareness and conservation in a lush, shady setting.", R.drawable.zooo),
                    Attraction("Water World Kelaniya", "Water World in Kelaniya is Sri Lanka’s only aquatic-themed park featuring both land and underwater exhibits. It showcases freshwater and marine life including sharks, stingrays, and exotic fish. The park also offers bird and animal sections, making it an educational and entertaining destination for kids and families near Colombo.", R.drawable.waterworld),
                    Attraction("Leisure World", "Leisure World in Avissawella is Sri Lanka’s first amusement and water park. It offers a wide range of attractions including water slides, wave pools, roller coasters, and kiddie rides. Ideal for family fun, school trips, and thrill-seekers, it’s a popular day-trip destination for recreation and relaxation near Colombo.", R.drawable.leisureworld)
                )
            )

            "Anuradhapura" -> Location(
                name = "Anuradhapura",
                headerImageResId = R.drawable.anuradapura3,
                attractions = listOf(
                    Attraction("Jaya Sri Maha Bodhi", "The Jaya Sri Maha Bodhi in Anuradhapura is the oldest historically documented tree in the world, grown from a cutting of the original Bodhi Tree under which the Buddha attained enlightenment. Revered by Buddhists, it is a key pilgrimage site, symbolizing peace, wisdom, and spiritual awakening in Sri Lanka.", R.drawable.jayasrimahabodhi),
                    Attraction("Ruwanweli Maha Seya", "Ruwanweli Maha Seya, also in Anuradhapura, is one of Sri Lanka’s most revered Buddhist stupas, built by King Dutugemunu in the 2nd century BCE. Known for its massive white dome and historical importance, it enshrines sacred relics of the Buddha and stands as a symbol of devotion and ancient Sinhalese architecture.", R.drawable.ruwanweliya),
                    Attraction("Thuparamaya", "Thuparamaya, located in Anuradhapura, is the first Buddhist stupa built in Sri Lanka after Buddhism was introduced. Constructed during King Devanampiyatissa’s reign, it enshrines the sacred collarbone relic of the Buddha. This ancient dagoba is a significant pilgrimage site and showcases early Buddhist architecture and peaceful spiritual surroundings.", R.drawable.thuparamaya),
                    Attraction("Samadhi Buddha Statue", "The Samadhi Buddha Statue in Anuradhapura depicts the Buddha in deep meditation (Dhyana Mudra). Carved from granite in the 4th century, it represents serenity and enlightenment. Set amidst a tranquil grove, this iconic sculpture is admired for its symmetry, calm expression, and spiritual aura that draws pilgrims and visitors alike.", R.drawable.samadhi),
                    Attraction("Isurumuniya Temple", "Isurumuniya is a rock temple in Anuradhapura known for its exquisite stone carvings, including the famous Isurumuni Lovers. Built during King Devanampiyatissa’s reign, it features serene shrines, ponds, and sculptures. The temple uniquely blends art, history, and nature, making it a culturally rich and romantic spiritual site.", R.drawable.isurumuniya),
                    Attraction("Mihinthale", "Mihintale, located near Anuradhapura, is the sacred mountain where Buddhism was introduced to Sri Lanka by Arahat Mahinda. It features ancient stairways, stupas, rock inscriptions, and meditation caves. Pilgrims climb the 1,800 steps to reach the summit and enjoy panoramic views and a spiritually enriching historical experience.", R.drawable.mihintale),
                    Attraction("Wilpattu National Park", "Wilpattu National Park, Sri Lanka’s largest and oldest national park, is known for its natural lakes (villus), leopards, elephants, sloth bears, and diverse birdlife. Located northwest of Anuradhapura, it offers jeep safaris through dense forest and open plains, ideal for wildlife enthusiasts and eco-tourism lovers seeking a less crowded experience.", R.drawable.wilpattu),
                    Attraction("Jethawanaramaya", "Jetavanaramaya is one of the tallest ancient brick structures in the world, located in Anuradhapura. Built by King Mahasena in the 3rd century AD, this massive stupa was once part of a powerful monastery complex. It symbolizes ancient engineering brilliance and remains a monumental relic of Sri Lanka’s Buddhist heritage.", R.drawable.jethawanaramaya)
                )
            )

            "Hambanthota" -> Location(
                name = "Hambanthota",
                headerImageResId = R.drawable.hambanthota,
                attractions = listOf(
                    Attraction("Mirijjawila Botanical Gardens", "Mirijjawila Botanical Garden, near Hambantota, showcases dry-zone flora and medicinal plants across 300 acres. Designed for research, conservation, and eco-tourism, it features beautiful landscaping, man-made lakes, and native trees. It offers visitors a peaceful retreat to explore plant biodiversity unique to Sri Lanka’s arid climate.", R.drawable.mirijja),
                    Attraction("Bataatha Agro Technology Park", "Bataatha Agro Technology Park, located near Ambalantota, is an agricultural education and demonstration center promoting modern farming techniques. With model farms, greenhouses, and exhibition areas, it educates farmers and students while attracting eco-tourists. It’s a scenic spot combining knowledge, innovation, and nature in one vibrant green space.", R.drawable.bataatha),
                    Attraction("Birds Park", "Birds Park in Hambantota is a vibrant aviary home to hundreds of exotic and native bird species. Spread across lush, landscaped gardens, the park offers walk-through aviaries and interactive experiences. It serves as an educational, conservation-focused, and family-friendly destination perfect for bird lovers and nature enthusiasts.", R.drawable.birdspark),
                    Attraction("Ridiyagama Safari Park", "Ridiyagama Safari Park, Sri Lanka’s first-ever open-air zoo, spans 500 acres in Hambantota. Divided into zones, it houses lions, elephants, zebras, and more in natural habitats. Visitors can enjoy guided safari rides through African and Asian zones, offering a safe and immersive wildlife experience close to nature.", R.drawable.ridiyagama),
                    Attraction("Tissamaharama Raja Maha Viharaya", "Tissamaharama Raja Maha Viharaya, located in the ancient town of Tissamaharama, is a significant Buddhist temple with a massive stupa believed to enshrine sacred relics. Built in the 2nd century BCE, it is a serene pilgrimage site surrounded by history, lotus ponds, and the peaceful atmosphere of Southern Sri Lanka.", R.drawable.thissa),
                    Attraction("Ranminithenna Tele Cinema Village", "Ranminithenna Tele Cinema Village near Tissamaharama is a purpose-built film set replicating ancient cities, colonial towns, and village scenes. It’s used for movies and teledramas while welcoming tourists for behind-the-scenes experiences. Visitors can explore film sets, dress in costume, and enjoy guided tours of this unique creative attraction.", R.drawable.ranminithanna),
                    Attraction("Yala National Park", "Yala National Park, Sri Lanka’s most famous wildlife reserve, is known for its high density of leopards, elephants, crocodiles, and diverse birdlife. Located in the southeast, it features dry forests, scrubland, and lagoons. Guided safaris offer thrilling encounters with wildlife in one of Asia’s top safari destinations.", R.drawable.yala),
                    Attraction("Magampura Mahinda Rajapaksa Port", "Magampura Mahinda Rajapaksa Port in Hambantota is Sri Lanka’s second-largest commercial port, strategically located along major maritime routes. Designed to boost trade and industrial development, it includes terminals, tank farms, and bunkering facilities. Though industrial in nature, it symbolizes Sri Lanka’s economic ambitions and growing role in global logistics.", R.drawable.magampuraport)
                )
            )






            else -> null
        }

        if (location == null) {
            Toast.makeText(this, "No location data found", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        // Start LocationDetailsActivity with full Location
        val intent = Intent(this, LocationDetailsActivity::class.java)
        intent.putExtra("destination", location)
        startActivity(intent)
        finish() // Optional: close this activity
    }
}
